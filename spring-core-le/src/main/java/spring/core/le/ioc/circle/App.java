package spring.core.le.ioc.circle;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 循环依赖问题
 *
 * @author Isen
 * @date 20-12-16 下午9:28
 * @since 1.0
 */
public class App {

    private static Map<String, RootBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    //一级缓存(单例池)
    //存储完整对象(依赖注入完)
    private final static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    //二级缓存
    //存储早期对象
    private final static Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    //三级缓存
    //存储对象工厂 用于提高性能
    private final static Map<String, ObjectFactory> singletonFactories = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        newApplicationContext();

        A a = (A) getBean("a");
        a.say();
    }

    private static void newApplicationContext() throws Exception {
        //加载bean定义
        loadBeanDefinition();

        for (String beanName : beanDefinitionMap.keySet()) {
//            getBean(beanName);
//            getBean2(beanName);
            getBean3(beanName);
        }
    }

    /**
     * 读取bean定义
     */
    private static void loadBeanDefinition() {
        RootBeanDefinition aBeanDefinition = new RootBeanDefinition(A.class);
        RootBeanDefinition bBeanDefinition = new RootBeanDefinition(B.class);
        RootBeanDefinition cBeanDefinition = new RootBeanDefinition(C.class);
        beanDefinitionMap.put("a", aBeanDefinition);
        beanDefinitionMap.put("b", bBeanDefinition);
        beanDefinitionMap.put("c", cBeanDefinition);
    }

    /**
     * 获取bean
     *
     * 通过一级缓存+同步锁解决循环依赖
     *
     * 性能低
     */
    private static Object getBean(String beanName) throws Exception {
        synchronized (singletonObjects) {
            //如果一级缓存中存在,表明已经创建
            if (singletonObjects.containsKey(beanName)) {
                return singletonObjects.get(beanName);
            }

            //获取bean定义
            RootBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

            //实例化
            Class<?> beanClass = beanDefinition.getBeanClass();
            Object beanInstance = beanClass.newInstance();

            //放到一级缓存,单例池
            singletonObjects.put(beanName, beanInstance);

            //属性注入
            Field[] declaredFields = beanClass.getDeclaredFields();
            for (Field field : declaredFields) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                if (autowired != null) {
                    String refBeanName = field.getName();

                    //递归的获取bean
                    Object refBean = getBean(refBeanName);

                    field.setAccessible(true);
                    field.set(beanInstance, refBean);
                }
            }

            //初始化/aware
            //...

            return beanInstance;
        }
    }

    /**
     * 获取bean
     *
     * 通过二级缓存 解决循环依赖, 性能高; 同时也解决循环依赖中的动态代理问题, 不过多次创建动态代理, 性能低
     */
    private static Object getBean2(String beanName) throws Exception {
        //如果一级缓存中存在,表明已经创建
        if (singletonObjects.containsKey(beanName)) {
            return singletonObjects.get(beanName);
        }

        synchronized (earlySingletonObjects) {
            if (earlySingletonObjects.containsKey(beanName)) {
                return earlySingletonObjects.get(beanName);
            }

            //获取bean定义
            RootBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

            //实例化
            Class<?> beanClass = beanDefinition.getBeanClass();
            Object beanInstance = beanClass.newInstance();

            //创建循环依赖中的动态代理(所有代理的创建位置都在这里,不符合规范)
            Object proxyBean = new CGLibProxyBeanPostProcessor().getEarlyBeanReference(beanInstance, beanName);
            earlySingletonObjects.put(beanName, proxyBean);

            //属性注入
            Field[] declaredFields = beanClass.getDeclaredFields();
            for (Field field : declaredFields) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                if (autowired != null) {
                    String refBeanName = field.getName();

                    //递归的获取bean
                    Object refBean = getBean2(refBeanName);

                    field.setAccessible(true);
                    field.set(beanInstance, refBean);
                }
            }

            //初始化/aware
            //...


            //创建通常的动态代理

            //放到一级缓存,单例池
            singletonObjects.put(beanName, proxyBean);
            return proxyBean;
        }
    }

    /**
     * 获取bean
     *
     * 通过三级缓存 解决循环依赖中的动态代理性能问题
     */
    private static Object getBean3(String beanName) throws Exception {
        //如果一级缓存中存在,表明已经创建
        if (singletonObjects.containsKey(beanName)) {
            return singletonObjects.get(beanName);
        }

        synchronized (earlySingletonObjects) {
            if (earlySingletonObjects.containsKey(beanName)) {
                //用于防止A的动态代理多次创建 A<->B A<->C
                return earlySingletonObjects.get(beanName);
            }

            if (singletonFactories.containsKey(beanName)) {
                ObjectFactory objectFactory =  singletonFactories.get(beanName);
                Object proxyBean = objectFactory.getObject();
                earlySingletonObjects.put(beanName, proxyBean);
                return proxyBean;
            }

            //获取bean定义
            RootBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

            //实例化
            Class<?> beanClass = beanDefinition.getBeanClass();
            Object beanInstance = beanClass.newInstance();

            //保存循环依赖中的动态代理创建创建工厂
            singletonFactories.put(beanName, () -> new CGLibProxyBeanPostProcessor().getEarlyBeanReference(beanInstance, beanName));

            //属性注入
            Field[] declaredFields = beanClass.getDeclaredFields();
            for (Field field : declaredFields) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                if (autowired != null) {
                    String refBeanName = field.getName();

                    //递归的获取bean
                    Object refBean = getBean3(refBeanName);

                    field.setAccessible(true);
                    field.set(beanInstance, refBean);
                }
            }

            //初始化/aware
            //...

            //创建通常的动态代理
            Object proxyBean = earlySingletonObjects.get(beanName);
            if (proxyBean == null) {
                ObjectFactory objectFactory =  singletonFactories.get(beanName);
                proxyBean = objectFactory.getObject();
                earlySingletonObjects.put(beanName, proxyBean);
            }

            //放到一级缓存,单例池
            singletonObjects.put(beanName, proxyBean);
            return proxyBean;
        }
    }
}

package spring.core.le.ioc.proxy;

/**
 * @author Isen
 * @date 20-12-16 下午10:52
 * @since 1.0
 */

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * CGLibProxy动态代理，可以代理接口或者类
 */
public class CGLibProxy implements MethodInterceptor {

    /**
     * CGLib需要代理的目标对象
     */
    private Object targetObject;

    /**
     * 为接口或类生成代理对象
     *
     * @param clazz 被代理的接口或类
     * @return 代理对象
     */
    @SuppressWarnings("unchecked")
    public <T> T newProxy(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    /**
     * 为类生成代理对象
     *
     * @param object 被代理的对象
     * @return 代理对象
     */
    public Object newProxy(Object object) {
        this.targetObject = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        if (targetObject != null) {
            //代理类
            System.out.println("调用被代理对象的方法开始");
            Object result = method.invoke(targetObject, args);
            System.out.println("调用被代理对象的方法结束");
            return result;
        } else {
            //代理接口
            System.out.println("方法调用开始");
            System.out.println("此处编写接口的实现代码");
            System.out.println("method toGenericString:" + method.toGenericString());
            System.out.println("method name:" + method.getName());
            System.out.println("method args:" + args.length);
            System.out.println("方法调用结束");
            return "方法调用结果";
        }
    }
}

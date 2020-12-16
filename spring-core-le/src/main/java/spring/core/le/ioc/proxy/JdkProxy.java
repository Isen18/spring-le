package spring.core.le.ioc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理，只能为接口或者实现接口的类进行代理
 */
public class JdkProxy implements InvocationHandler {

    private Object targetObject;

    /**
     * 为接口生成代理对象
     * @param inter 被代理的接口
     * @return 代理对象
     */
    @SuppressWarnings("unchecked")
    public <T> T newProxy(Class<T> inter) {
        if(!Modifier.isInterface(inter.getModifiers())){
            throw new IllegalArgumentException(String.format("inter=%s 不是接口", inter.getName()));
        }
        //类加载器+接口+代理类
        return (T)Proxy.newProxyInstance(inter.getClassLoader(), new Class<?>[]{inter}, this);
    }

    /**
     * 为接口实现类生成代理对象
     * @param object 被代理的对象
     * @return 代理对象
     */
    public Object newProxy(Object object){
        Class<?> clazz = object.getClass();
        if(clazz.getInterfaces().length == 0){
            throw new IllegalArgumentException(String.format("object.getClass=%s 没有 implements 接口", clazz.getName()));
        }

        targetObject = object;

        //类加载器+接口+代理类
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws InvocationTargetException, IllegalAccessException {
        if(targetObject != null){
            //代理接口实现类
            System.out.println("调用被代理对象的方法开始");
            Object result = method.invoke(targetObject, args);
            System.out.println("调用被代理对象的方法结束");
            return result;
        }else {
            //代理接口
            System.out.println("接口方法调用开始");
            System.out.println("此处编写接口的实现代码");
            System.out.println("method toGenericString:" + method.toGenericString());
            System.out.println("method name:" + method.getName());
            System.out.println("method args:" + args.length);
            System.out.println("接口方法调用结束");
            return "方法调用结果";
        }
    }
}

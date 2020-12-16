package spring.core.le.ioc.circle;

import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import spring.core.le.ioc.proxy.CGLibProxy;

/**
 * @author Isen
 * @date 20-12-16 下午10:07
 * @since 1.0
 */
public class CGLibProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    public Object getEarlyBeanReference(Object bean, String beanName) {

        //假设: A被切点命中 需要创建动态代理
        if (bean instanceof A) {
            CGLibProxy cgLibProxy = new CGLibProxy();
            return cgLibProxy.newProxy(bean);
        }

        return bean;
    }
}

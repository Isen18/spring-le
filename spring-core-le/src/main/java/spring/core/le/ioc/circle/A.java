package spring.core.le.ioc.circle;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Isen
 * @date 20-12-16 下午9:25
 * @since 1.0
 */
public class A {

    @Autowired
    private B b;


    @Autowired
    private C c;

    public void say() {
        System.out.println("Hello world!");
    }
}

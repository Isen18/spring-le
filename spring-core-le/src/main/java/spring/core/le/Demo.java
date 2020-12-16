package spring.core.le;

import org.springframework.context.ApplicationContext;

/**
 * @author Isen
 * @date 2018/12/26 16:53
 * @since 1.0
 */
public class Demo {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext.getBean("aa");
    }
}

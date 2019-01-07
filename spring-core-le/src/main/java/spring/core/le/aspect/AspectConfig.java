package spring.core.le.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Isen
 * @date 2018/12/26 23:48
 * @since 1.0
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AspectConfig {
    @Bean
    public AddFunctionAspect addFunctionAspect(){
        return new AddFunctionAspect();
    }
}

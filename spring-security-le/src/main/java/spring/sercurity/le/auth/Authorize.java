package spring.sercurity.le.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Isen
 * @date 19-6-25 下午9:48
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorize {
    // XXX isen 19-6-25 参考spring-security preAuthorize
    MethodPermissionEn[] value();
}

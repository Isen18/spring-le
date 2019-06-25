package spring.sercurity.le.permission;

import java.io.Serializable;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import spring.sercurity.le.domain.Spittle;

/**
 * @author Isen
 * @date 19-6-17 下午11:33
 * @since 1.0
 */
public class SpittlePermissionEvaluator implements PermissionEvaluator {
    private static final GrantedAuthority ADMIN_AUTHORITY = () -> "ROLE_ADMIN";

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if(targetDomainObject instanceof Spittle){
            Spittle spittle = (Spittle)targetDomainObject;
            String username = spittle.getSpitter().getUsername();
            if("delete".equals(permission)){
                return isAdmin(authentication) || username.equals(authentication.getName());
            }
        }

        throw new UnsupportedOperationException("hasPermission not supported for object <" + targetDomainObject + "> and permission<" + permission + ">");
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }

    private boolean isAdmin(Authentication authentication){
        return authentication.getAuthorities().contains(ADMIN_AUTHORITY);
    }
}

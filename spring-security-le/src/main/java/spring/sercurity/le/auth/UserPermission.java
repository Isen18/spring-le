package spring.sercurity.le.auth;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Isen
 * @date 19-6-25 下午9:59
 * @since 1.0
 */
public class UserPermission {

    /**
     * url权限模式集合,包含的元素为url模式,例如/item/**
     */
    Set<String> urlPermissionPatternSet = new HashSet<>();

    /**
     * method权限集合,包含的原始为方法权限标识,例如item_add
     */
    Set<String> methodPermissionSet = new HashSet<>();

    public Set<String> getUrlPermissionPatternSet() {
        return urlPermissionPatternSet;
    }

    public void setUrlPermissionPatternSet(Set<String> urlPermissionPatternSet) {
        this.urlPermissionPatternSet = urlPermissionPatternSet;
    }

    public Set<String> getMethodPermissionSet() {
        return methodPermissionSet;
    }

    public void setMethodPermissionSet(Set<String> methodPermissionSet) {
        this.methodPermissionSet = methodPermissionSet;
    }
}

package spring.sercurity.le.auth;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.CollectionUtils;

/**
 * 权限计算器
 *
 * @author Isen
 * @date 19-6-25 下午9:40
 * @since 1.0
 */
public class PermissionEvaluator {

    /**
     * 应用所有的url权限模式
     */
    private static Set<String> allUrlPermissionPatternSet;

    /**
     * url层判断是否有权限
     * @param userPermission 用户权限
     * @param request 请求
     * @return true:有权限,false:无权限
     */
    public static boolean hasPermission(UserPermission userPermission, HttpServletRequest request) {
        // TODO isen 19-6-25 更新allUrlPermissionPatternSet
        if(allUrlPermissionPatternSet == null){
            allUrlPermissionPatternSet = getAllUrlPermissionPatter();
        }

        if(CollectionUtils.isEmpty(allUrlPermissionPatternSet)){
            //url没有要求授权
            return true;
        }

        if(userPermission == null){
            return false;
        }

        // TODO isen 19-6-25 参考springmvc HandlerMapping
        for(String urlPermissionPattern : allUrlPermissionPatternSet){
            // XXX isen 19-6-25 缓存, 不应该每次都进行new AntPathRequestMatcher
            AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(urlPermissionPattern);
            // XXX isen 19-6-25 优化:最匹配
            if(antPathRequestMatcher.matches(request)){
                Set<String> urlPermissionPatternSet = userPermission.getUrlPermissionPatternSet();
                if(CollectionUtils.isEmpty(urlPermissionPatternSet)){
                    return false;
                }

                if(urlPermissionPatternSet.contains(urlPermissionPattern)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * method层判断是否有权限
     * @param userPermission 用户权限
     * @param authorize 权限注解
     * @return true:有权限,false:无权限
     */
    public static boolean hasPermission(UserPermission userPermission, Authorize authorize) {
        if(authorize == null){
            //方法没有要求需要授权
            return true;
        }

        if(userPermission == null){
            return false;
        }

        Set<String> permissionMarkSet = Arrays.stream(authorize.value()).map(MethodPermissionEn::getPermissionMark).collect(Collectors.toSet());
        Set<String> methodPermissionSet = userPermission.getMethodPermissionSet();
        if(CollectionUtils.isEmpty(methodPermissionSet)){
            return false;
        }

        if(methodPermissionSet.containsAll(permissionMarkSet)){
            // XXX isen 19-6-25 记录缺少什么权限
            return true;
        }

        return false;
    }

    /**
     * 获取应用所有的url权限模式
     * @return url权限模式集合
     */
    private static Set<String> getAllUrlPermissionPatter(){
        // TODO isen 19-6-25
        return new HashSet<>();
    }
}

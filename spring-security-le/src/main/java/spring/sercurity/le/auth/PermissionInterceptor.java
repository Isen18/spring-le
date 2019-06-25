package spring.sercurity.le.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Isen
 * @date 19-6-25 下午9:40
 * @since 1.0
 */
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            throw new UnsupportedOperationException("not support handler that's not a HandlerMethod");
        }

        //获取当前用户权限
        UserPermission userPermission = getUserPermission(request);
        //1. url层授权认证
        boolean urlPermit = PermissionEvaluator.hasPermission(userPermission, request);
        if(urlPermit){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Authorize authorize =  handlerMethod.getMethod().getAnnotation(Authorize.class);

            //2. 方法层授权认证
            if(PermissionEvaluator.hasPermission(userPermission, authorize)){
                return true;
            }
        }

        //无权访问
        // TODO isen 19-6-25 无权提示
        return false;
    }

    private UserPermission getUserPermission(HttpServletRequest request){
        // TODO isen 19-6-25 获取用户权限
        return null;
    }
}

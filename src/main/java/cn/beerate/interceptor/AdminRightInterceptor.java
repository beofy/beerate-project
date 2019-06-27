package cn.beerate.interceptor;

import cn.beerate.constant.SessionKey;
import cn.beerate.exception.AdminRightException;
import cn.beerate.model.entity.t_right;
import cn.beerate.request.RequestProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 后台权限拦截
 */
@Component
public class AdminRightInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断控制器方法是否存在
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        /*
         * 注意，这里的request对象并未进行代理类进行适配，要手动调用适配器，否则无法判断用户登录状态
         */
        HttpServletRequest requestProxy = new RequestProxy(request);
        HttpSession session = requestProxy.getSession();

        @SuppressWarnings("unchecked")
        Map<Long, t_right>  rightMap = (Map<Long, t_right>)session.getAttribute(SessionKey.ADMIN_SESSION_RIGHT);
        if(rightMap.isEmpty()){
            throw new AdminRightException("无操作权限");
        }

        String uri = request.getRequestURI();

        boolean hasRight=false;
        for (Map.Entry<Long, t_right> entry : rightMap.entrySet()) {
            if (entry.getValue().getAction().equalsIgnoreCase(uri)){
                hasRight=true;
            }
        }
        if (!hasRight){
            throw new AdminRightException("无操作权限");
        }

        return true;
    }
}

package cn.beerate.interceptor;

import cn.beerate.constant.SessionKey;
import cn.beerate.exception.AdminLoginException;
import cn.beerate.request.RequestProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 后台登陆拦截
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

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
        if(session.getAttribute(SessionKey.ADMIN_SESSION_KEY)==null){
           throw new AdminLoginException("管理员未登录");
        }

        return true;
    }
}

package cn.beerate.interceptor;

import cn.beerate.constant.SessionKey;
import cn.beerate.exception.NoApproveException;
import cn.beerate.model.bean.User;
import cn.beerate.request.RequestProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 名片认证拦截
 */
@Component
public class BusinessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletRequest requestProxy = new RequestProxy(request);
        User user =(User) requestProxy.getSession().getAttribute(SessionKey.USER_SESSION_KEY);
        if(!user.isApprove()){
            throw new NoApproveException("用户未认证");
        }
        return true;
    }

}

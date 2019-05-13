package cn.beerate.interceptor;

import cn.beerate.constant.SessionKey;
import cn.beerate.exception.UserLoginException;
import cn.beerate.model.bean.User;
import cn.beerate.request.RequestProxy;
import cn.beerate.utils.StringUtil;
import cn.beerate.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户登录拦截器
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

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
        Object object =session.getAttribute(SessionKey.USER_SESSION_KEY);
        if(object==null){
            throw new UserLoginException("用户未登录");
        }

        String token = requestProxy.getHeader("token");
        if(StringUtils.isBlank(token)){
            throw new UserLoginException("系统异常");
        }

        if(!token.equals(((User)object).getToken())){
            throw new UserLoginException("登录超时");
        }

        if(TokenUtil.validToken(token)==null){
            throw new UserLoginException("请求超时");
        }

        return true;
    }

}

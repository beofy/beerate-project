package cn.beerate.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码拦截
 */
@Component
public class CaptchaInterceptor implements HandlerInterceptor{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String captchaCode = request.getParameter("captchacode");

        if(StringUtils.isBlank(captchaCode)){
           throw  new IllegalArgumentException("验证码错误");
        }



        return true;
    }
}

package cn.beerate.config;

import cn.beerate.interceptor.CallCardInterceptor;
import cn.beerate.interceptor.RealNameInterceptor;
import cn.beerate.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 前台配置
 */
@Configuration
public class UserConfig implements WebMvcConfigurer {

    private UserLoginInterceptor userLoginInterceptor;
    private CallCardInterceptor callCardInterceptor;
    private RealNameInterceptor realNameInterceptor;

    public UserConfig(UserLoginInterceptor userLoginInterceptor, CallCardInterceptor callCardInterceptor, RealNameInterceptor realNameInterceptor) {
        this.userLoginInterceptor = userLoginInterceptor;
        this.callCardInterceptor = callCardInterceptor;
        this.realNameInterceptor = realNameInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加登录拦截
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login","/user/registe");

    }
}

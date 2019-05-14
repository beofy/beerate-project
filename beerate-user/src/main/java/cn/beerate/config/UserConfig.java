package cn.beerate.config;

import cn.beerate.interceptor.BusinessInterceptor;
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
    private BusinessInterceptor businessInterceptor;

    public UserConfig(UserLoginInterceptor userLoginInterceptor, BusinessInterceptor businessInterceptor) {
        this.userLoginInterceptor = userLoginInterceptor;
        this.businessInterceptor = businessInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加登录拦截
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login","/user/reg");

        //名片认证拦截
        registry.addInterceptor(businessInterceptor)
                .addPathPatterns("/user/*/*")
                .excludePathPatterns("/user/business/upload","/user/business/supplement","/user/business/detail");

    }
}

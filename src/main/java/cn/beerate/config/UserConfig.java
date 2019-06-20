package cn.beerate.config;

import cn.beerate.interceptor.BusinessInterceptor;
import cn.beerate.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

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

        List<String> list = new ArrayList<>();
        list.add("/user/projectSet/loan");
        list.add("/user/projectSet/blockTrade");
        list.add("/user/projectSet/preIpo");
        list.add("/user/projectSet/stockPledge");
        list.add("/user/projectSet/stockTransfer");
        list.add("/user/projectSet/list");
        String[] excludePathPatterns = new String[list.size()];

        //添加登录拦截
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login","/user/reg","/user/resetUserPassWord")
                .excludePathPatterns(list.toArray(excludePathPatterns));


        //名片认证拦截
        registry.addInterceptor(businessInterceptor)
                .addPathPatterns(
                        "/user/*/**"
                )
                .excludePathPatterns(
                        "/user/*",
                        "/user/business/*"
                )
                .excludePathPatterns(list.toArray(excludePathPatterns));
    }
}

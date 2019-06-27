package cn.beerate.config;

import cn.beerate.interceptor.AdminLoginInterceptor;
import cn.beerate.interceptor.AdminRightInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminConfig implements WebMvcConfigurer {

    private AdminLoginInterceptor adminLoginInterceptor;
    private AdminRightInterceptor adminRightInterceptor;

    public AdminConfig(AdminLoginInterceptor adminLoginInterceptor, AdminRightInterceptor adminRightInterceptor) {
        this.adminLoginInterceptor = adminLoginInterceptor;
        this.adminRightInterceptor = adminRightInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**","/platform/**").excludePathPatterns("/admin/login.html","/admin/login");

        registry.addInterceptor(adminRightInterceptor).addPathPatterns("/admin/**").excludePathPatterns(
                "/admin/login.html",
                "/admin/login",
                "/admin/loginOut",
                "/admin/me.html",
                "/admin/updatePassWord.html"
        );
    }
}

package cn.beerate.config;


import cn.beerate.interceptor.CaptchaInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCaptchaConfig implements WebMvcConfigurer {

	@Autowired
	private CaptchaInterceptor captchaInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(captchaInterceptor).addPathPatterns("/admin/login");
	}



}

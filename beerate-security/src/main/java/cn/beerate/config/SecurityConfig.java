package cn.beerate.config;


import cn.beerate.request.RequestProvidor;
import cn.beerate.captcha.email.EmailSender;
import cn.beerate.captcha.email.IEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public HttpServletRequest requestProvidor(HttpServletRequest httpServletRequest){
        return new RequestProvidor(httpServletRequest);
    }

    @Bean
    public IEmail emailSender(){
        return new EmailSender("smtp.mxhichina.com","","");
    }

}

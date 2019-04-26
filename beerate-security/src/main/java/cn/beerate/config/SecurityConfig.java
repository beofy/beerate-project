package cn.beerate.config;


import cn.beerate.Properties;
import cn.beerate.PropertiesHodler;
import cn.beerate.captcha.email.EmailSender;
import cn.beerate.captcha.email.IEmail;
import cn.beerate.captcha.mobile.ISms;
import cn.beerate.captcha.mobile.chuanglan.ChuangLanSms;
import cn.beerate.request.RequestProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private static final Log logger = LogFactory.getLog(SecurityConfig.class);

    private Properties properties;

    public SecurityConfig(Properties properties) {
        this.properties = properties;
    }

    @Bean
    public HttpServletRequest request(HttpServletRequest httpServletRequest){
        return new RequestProxy(httpServletRequest);
    }

    @Bean
    public IEmail emailSender(){
        return new EmailSender("smtp.mxhichina.com","","","");
    }

    @Bean
    public ISms chuangLanSms(){
        return new ChuangLanSms( "", "");
    }

    @Bean
    void initPropertiesHodler(){
        logger.info("初始化系统参数");
        PropertiesHodler.properties=properties;
    }


}

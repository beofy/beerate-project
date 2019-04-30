package cn.beerate.config;


import cn.beerate.Properties;
import cn.beerate.PropertiesHolder;
import cn.beerate.captcha.email.EmailSender;
import cn.beerate.captcha.email.IEmail;
import cn.beerate.captcha.mobile.ISms;
import cn.beerate.captcha.mobile.chuanglan.ChuangLanSms;
import cn.beerate.oss.LocalOSSImpl;
import cn.beerate.oss.OSS;
import cn.beerate.request.RequestProxy;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private final Log logger = LogFactory.getLog(this.getClass());

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
    void initPropertiesHolder(){
        logger.info("初始化系统参数");
        PropertiesHolder.properties=properties;
    }

    @Bean(initMethod = "init")
    @DependsOn("initPropertiesHolder")
    public OSS oss(){
        String fileStoreImpl = properties.getFileProperties().getFileStoreImpl();
        logger.info(String.format("OSS服务接口实例化:%s",fileStoreImpl));
        if(StringUtils.isNotBlank(fileStoreImpl)){
            try {
                return (OSS) Class.forName(fileStoreImpl).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return new LocalOSSImpl();
    }

}

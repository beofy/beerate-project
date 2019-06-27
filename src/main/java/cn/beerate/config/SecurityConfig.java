package cn.beerate.config;


import cn.beerate.Properties;
import cn.beerate.PropertiesHolder;
import cn.beerate.captcha.email.EmailSender;
import cn.beerate.captcha.email.IEmail;
import cn.beerate.captcha.mobile.ISms;
import cn.beerate.captcha.mobile.chuanglan.ChuangLanSms;
import cn.beerate.constant.PlatformSettingKey;
import cn.beerate.model.entity.t_platform_setting;
import cn.beerate.request.RequestProxy;
import cn.beerate.service.PlatformSettingService;
import cn.beerate.utils.PathUtil;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private final Log logger = LogFactory.getLog(this.getClass());
    private Properties properties;

    @Autowired
    private PlatformSettingService platformSettingService;

    public SecurityConfig(Properties properties){
        this.properties = properties;
    }

    @Bean
    public HttpServletRequest request(HttpServletRequest httpServletRequest){
        return new RequestProxy(httpServletRequest);
    }

    @Bean
    public IEmail emailSender(){
        t_platform_setting hostName= platformSettingService.findBySetKey(PlatformSettingKey.SMTP_SERVER);
        t_platform_setting  mailAccount= platformSettingService.findBySetKey(PlatformSettingKey.MAIL_ACCOUNT);
        t_platform_setting  mailPassword= platformSettingService.findBySetKey(PlatformSettingKey.MAIL_PASSWORD);
        t_platform_setting  smtpPort= platformSettingService.findBySetKey(PlatformSettingKey.SMTP_PORT);

        return new EmailSender(hostName.getSetValue(),mailAccount.getSetValue(),mailPassword.getSetValue(),smtpPort.getSetValue());
    }

    @Bean
    public ISms chuangLanSms(){
        t_platform_setting  smsAccountSetting= platformSettingService.findBySetKey(PlatformSettingKey.SMS_ACCOUNT);
        t_platform_setting  smsPasswordSetting= platformSettingService.findBySetKey(PlatformSettingKey.SMS_PASSWORD);

        return new ChuangLanSms( smsAccountSetting.getSetValue(), smsPasswordSetting.getSetValue());
    }

    @Bean
    void initPropertiesHolder(){
        logger.info("初始化系统参数");
        PropertiesHolder.properties=properties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/","file:"+PathUtil.getRoot()+"attachment");

        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    @Bean
    @SuppressWarnings("all")
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(SerializerFeature.EMPTY);

        // 3.在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);


        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        // 4.将converter赋值给HttpMessageConverter
        HttpMessageConverter<?> converter = fastConverter;

        // 5.返回HttpMessageConverters对象
        return new HttpMessageConverters(converter);
    }

}

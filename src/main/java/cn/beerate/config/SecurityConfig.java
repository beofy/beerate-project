package cn.beerate.config;


import cn.beerate.Properties;
import cn.beerate.PropertiesHolder;
import cn.beerate.captcha.email.EmailSender;
import cn.beerate.captcha.email.IEmail;
import cn.beerate.captcha.mobile.ISms;
import cn.beerate.captcha.mobile.chuanglan.ChuangLanSms;
import cn.beerate.request.RequestProxy;
import cn.beerate.utils.PathUtil;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/","file:"+PathUtil.getRoot()+"attachment");
    }

    @Bean

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
        @SuppressWarnings("unchecked")
        HttpMessageConverter<?> converter = fastConverter;

        // 5.返回HttpMessageConverters对象
        return new HttpMessageConverters(converter);
    }

}

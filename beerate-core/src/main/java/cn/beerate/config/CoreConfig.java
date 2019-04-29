package cn.beerate.config;

import cn.beerate.PropertiesHolder;
import cn.beerate.oss.OSS;
import cn.beerate.oss.LocalOSSImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 核心模块配置
 */
@Configuration
public class CoreConfig {

    @Bean
    public OSS oss(){
        String fileStoreImpl = PropertiesHolder.properties.getFileProperties().getFileStoreImpl();
        if(StringUtils.isNotBlank(fileStoreImpl)){
            try {
                return (OSS) Class.forName(fileStoreImpl).newInstance();
            } catch (InstantiationException | IllegalAccessException| ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return new LocalOSSImpl();
    }


}

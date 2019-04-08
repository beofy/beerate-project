package cn.beerate.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    /**
     * 实例化缓存组件
     */
    @Bean(initMethod = "init",destroyMethod = "stop")
    public Cache cache(){
        return new Cache();
    }

}

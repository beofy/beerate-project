package cn.beerate;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Properties {

    private SecurityProperties securityProperties;

    public Properties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }
}

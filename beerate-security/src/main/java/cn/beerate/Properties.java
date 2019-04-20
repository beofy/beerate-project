package cn.beerate;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Properties {

    private SecurityProperties securityProperties;
    private FileProperties fileProperties;

    public Properties(SecurityProperties securityProperties, FileProperties fileProperties) {
        this.securityProperties = securityProperties;
        this.fileProperties = fileProperties;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public FileProperties getFileProperties() {
        return fileProperties;
    }


}

package cn.beerate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 安全配置
 */
@Configuration
@ConfigurationProperties(prefix = "cn.beerate.security")
public class SecurityProperties {
    /**
     * 密码加密参数
     */
    private String password_md5_salt;

    /**
     * 对称加密参数
     */
    private String des_encrypt_key;

    public String getDes_encrypt_key() {
        return des_encrypt_key;
    }

    public void setDes_encrypt_key(String des_encrypt_key) {
        this.des_encrypt_key = des_encrypt_key;
    }

    public String getPassword_md5_salt() {
        return password_md5_salt;
    }

    public void setPassword_md5_salt(String password_md5_salt) {
        this.password_md5_salt = password_md5_salt;
    }
}

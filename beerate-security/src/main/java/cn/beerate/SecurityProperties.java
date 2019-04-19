package cn.beerate;

/**
 * 安全配置
 */
public class SecurityProperties {
    /**
     * 密码加密参数
     */
    private String password_md5_salt;

    public String getPassword_md5_salt() {
        return password_md5_salt;
    }

    public void setPassword_md5_salt(String password_md5_salt) {
        this.password_md5_salt = password_md5_salt;
    }
}

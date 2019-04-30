package cn.beerate.captcha;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CaptchaCode implements CaptchaGenerator, Serializable {

    public CaptchaCode(String captchaCode, int expireIn) {
        this.captchaCode = captchaCode;
        this.expireIn = expireIn;
        this.expireTime=LocalDateTime.now().plusSeconds(this.expireIn);
    }

    /**
     *  验证码
     */
    private String captchaCode;

    /**
     * 超时时间（秒）
     */
    private int  expireIn;

    /**
     * 超时时间
     */
    private LocalDateTime expireTime;


    /**
     * 检查验证码
     */
    @Override
    public boolean check(String captchaCode) {

        return captchaCode.equalsIgnoreCase(this.captchaCode);
    }

    /**
     * 验证码是否有效
     */
    @Override
    public boolean checkExpireIn() {
        return LocalDateTime.now().isBefore(expireTime);
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

}

package cn.beerate.captcha;

import java.time.LocalDateTime;

public class CaptchaCode implements CaptchaGenerator{

    public CaptchaCode(String captchaCode, int expireIn) {
        this.captchaCode = captchaCode;
        this.expireIn = expireIn;
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
     * 验证码是否过期
     */
    @Override
    public boolean checkExpireIn() {
        return expireTime.isBefore(LocalDateTime.now());
    }


    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
        this.expireTime=LocalDateTime.now().plusSeconds(expireIn);
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

}

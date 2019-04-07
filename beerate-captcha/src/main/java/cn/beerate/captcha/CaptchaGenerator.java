package cn.beerate.captcha;

/**
 * 验证码生成器
 */
public interface CaptchaGenerator {

    /**
     * 验证码是否正确
     */
    boolean check(String captchaCode);

    /**
     * 验证码是否到期
     */
    boolean checkExpireIn();

}

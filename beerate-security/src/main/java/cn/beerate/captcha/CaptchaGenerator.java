package cn.beerate.captcha;

import java.time.LocalDateTime;

/**
 * 验证码生成器
 */
public interface CaptchaGenerator {


    /**
     * 获取验证码
     */
    String getCaptchaCode();

    /**
     * 获取超时时间（秒）
     */
    int getExpireIn();

    /**
     * 获取到期时间
     */
    LocalDateTime getExpireTime();


    /**
     * 验证码是否正确
     */
    boolean check(String captchaCode);

    /**
     * 验证码是否到期
     */
    boolean checkExpireIn();
}

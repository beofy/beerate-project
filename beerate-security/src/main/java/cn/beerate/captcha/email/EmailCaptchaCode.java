package cn.beerate.captcha.email;

import cn.beerate.captcha.CaptchaCode;
import cn.beerate.captcha.CaptchaGenerator;

public class EmailCaptchaCode extends CaptchaCode implements CaptchaGenerator {

    /**
     * 邮箱
     */
    private String email;

    EmailCaptchaCode(String captchaCode, int expireIn, String email) {
        super(captchaCode, expireIn);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}

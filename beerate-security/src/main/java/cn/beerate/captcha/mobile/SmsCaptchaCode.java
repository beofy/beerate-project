package cn.beerate.captcha.mobile;

import cn.beerate.captcha.CaptchaCode;
import cn.beerate.captcha.CaptchaGenerator;

public class SmsCaptchaCode extends CaptchaCode implements CaptchaGenerator {

    /**
     * 手机号
     */
    private String mobile;

    SmsCaptchaCode(String captchaCode, int expireIn,String mobile) {
        super(captchaCode, expireIn);
        this.mobile=mobile;
    }

    public String getMobile() {
        return mobile;
    }

}

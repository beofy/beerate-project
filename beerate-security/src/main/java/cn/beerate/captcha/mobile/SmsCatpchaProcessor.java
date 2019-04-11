package cn.beerate.captcha.mobile;

import cn.beerate.captcha.AbstractCaptchaProcessor;
import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaGenerator;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.common.Message;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 手机验证码处理器
 */
@Component
public class SmsCatpchaProcessor extends AbstractCaptchaProcessor implements CaptchaProcessor {

    @Override
    public Message<String> send(HttpServletRequest request, HttpServletResponse response, CaptchaGenerator captchaGenerator) throws IOException {

        return Message.ok("发送成功");
    }

    @Override
    public boolean support(Captcha captcha) {
        return Captcha.SMS==captcha;
    }
}

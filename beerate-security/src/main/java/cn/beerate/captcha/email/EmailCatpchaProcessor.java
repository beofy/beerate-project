package cn.beerate.captcha.email;

import cn.beerate.captcha.AbstractCaptchaProcessor;
import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaGenerator;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.common.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 邮件验证码处理器
 */
@Component
public class EmailCatpchaProcessor extends AbstractCaptchaProcessor implements CaptchaProcessor {

    @Autowired
    private IEmail emailSender;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Message<String> send(HttpServletRequest request, HttpServletResponse response, CaptchaGenerator captchaGenerator) throws IOException {
        String toEmail = request.getParameter("toemail");
        emailSender.sendEmail(toEmail,"巴雷特","验证码：["+captchaGenerator.getCaptchaCode()+"]");

        return Message.ok("发送成功");
    }

    @Override
    public boolean support(Captcha captcha) {
        return Captcha.EMAIL==captcha;
    }
}

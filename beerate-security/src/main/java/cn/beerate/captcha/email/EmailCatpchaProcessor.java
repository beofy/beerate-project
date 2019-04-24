package cn.beerate.captcha.email;

import cn.beerate.Utils.StringUtil;
import cn.beerate.captcha.AbstractCaptchaProcessor;
import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaGenerator;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.common.Message;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 邮件验证码处理器
 */
@Component
public class EmailCatpchaProcessor extends AbstractCaptchaProcessor implements CaptchaProcessor {

    private IEmail emailSender;
    public EmailCatpchaProcessor(IEmail emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public Message<String> send(HttpServletRequest request, HttpServletResponse response, CaptchaGenerator captchaGenerator){
        String toEmail = request.getParameter("toemail");

        if(!StringUtil.isEmail(toEmail)){
           return Message.error("请输入正确的电子邮箱");
        }

        emailSender.sendEmail(toEmail,"巴雷特","验证码：["+captchaGenerator.getCaptchaCode()+"]");
        return Message.ok("发送成功");
    }

    @Override
    public boolean support(Captcha captcha) {
        return Captcha.EMAIL==captcha;
    }
}

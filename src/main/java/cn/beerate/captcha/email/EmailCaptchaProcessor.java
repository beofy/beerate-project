package cn.beerate.captcha.email;

import cn.beerate.utils.StringUtil;
import cn.beerate.captcha.*;
import cn.beerate.common.Message;
import cn.beerate.exception.ExceptionHandle;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 邮件验证码处理器
 */
@Component
public class EmailCaptchaProcessor extends AbstractCaptchaProcessor implements CaptchaProcessor {

    private static final Log logger = LogFactory.getLog(ExceptionHandle.class);

    private IEmail emailSender;
    public EmailCaptchaProcessor(IEmail emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public Message<String> send(HttpServletRequest request, HttpServletResponse response, CaptchaGenerator captchaGenerator){
        String email =((EmailCaptchaCode)captchaGenerator).getEmail();
        emailSender.sendEmail(email,"巴雷特","验证码：["+captchaGenerator.getCaptchaCode()+"]");

        logger.info(String.format("发送邮件：%s,验证码：[%s]",email,captchaGenerator.getCaptchaCode()));

        return Message.ok("发送成功");
    }

    @Override
    public Message<String> create(HttpServletRequest request, HttpServletResponse response, CaptchaScene captchaScene, Captcha captcha){

        Message<String> message = getEmail(request);
        if(message.fail()){
            return message;
        }

        EmailCaptchaCode emailCaptchaCode = new EmailCaptchaCode(RandomStringUtils.randomNumeric(6),300,message.getData());
        save(request,captcha,captchaScene,emailCaptchaCode);

        return send(request,response,emailCaptchaCode);
    }


    @Override
    public Message<String> check(HttpServletRequest request, Captcha captcha, CaptchaScene captchaScene, String captchaCode) {
        EmailCaptchaCode smsCaptchaCode = (EmailCaptchaCode)request.getSession().getAttribute(getSessionKey(captcha,captchaScene));
        //验证码是否存在
        if(smsCaptchaCode==null){
            return Message.error("验证码不存在");
        }

        //邮箱是否匹配
        if (!getEmail(request).getData().equals(smsCaptchaCode.getEmail())){
            return Message.error("系统异常");
        }

        //验证码判断
        if (!captchaCode.equalsIgnoreCase(smsCaptchaCode.getCaptchaCode())){

            return Message.error("验证码错误");
        }

        //验证码是否过期
        if (!smsCaptchaCode.checkExpireIn()){
            return Message.error("验证码已过期");
        }

        return Message.ok("验证码校验成功");
    }

    @Override
    public boolean support(Captcha captcha) {
        return Captcha.EMAIL==captcha;
    }

    /**
     * 获取邮箱
     */
    private Message<String> getEmail(HttpServletRequest request){

        String email = request.getParameter("email");
        if(!StringUtil.isEmail(email)){
            return Message.error("请输入正确的电子邮箱");
        }

        return Message.success(email);
    }
}

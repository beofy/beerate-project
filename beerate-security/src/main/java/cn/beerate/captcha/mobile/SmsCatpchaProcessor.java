package cn.beerate.captcha.mobile;

import cn.beerate.Utils.StringUtil;
import cn.beerate.captcha.*;
import cn.beerate.common.Message;
import cn.beerate.exception.ExceptionHandle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 手机验证码处理器
 */
@Component
public class SmsCatpchaProcessor extends AbstractCaptchaProcessor implements CaptchaProcessor {
    private static final Log logger = LogFactory.getLog(ExceptionHandle.class);
    private ISms chuangLanSms;
    public SmsCatpchaProcessor(ISms chuangLanSms) {
        this.chuangLanSms = chuangLanSms;
    }


    @Override
    public Message<String> create(HttpServletRequest request, HttpServletResponse response, CaptchaScene captchaScene, Captcha captcha) throws IOException {
        final String CAPTCHA_SESSION_KEY = PREFIX+captcha.name()+"_"+captchaScene.name();
        //获取session储存的验证码
        CaptchaGenerator captchaGenerator = (CaptchaGenerator)request.getSession().getAttribute(CAPTCHA_SESSION_KEY);
        /*
         * 这里针对短信，做增强，判断验证码是否过期，没过期则不发送新的短信验证码
         */
        if(captchaGenerator!=null&&captchaGenerator.checkExpireIn()){
            return Message.error("短信已发送，请稍候再试");
        }

        return super.create(request, response, captchaScene, captcha);
    }

    @Override
    public Message<String> send(HttpServletRequest request, HttpServletResponse response, CaptchaGenerator captchaGenerator) {
        String mobile =  request.getParameter("mobile");
        if(!StringUtil.isMobile(mobile)){
            return Message.error("请输入正确的手机号码");
        }

        logger.info(String.format("手机号：%s,验证码：[%s]",mobile,captchaGenerator.getCaptchaCode()));
        chuangLanSms.sendSMS(mobile,"验证码：["+captchaGenerator.getCaptchaCode()+"]");
        return Message.ok("发送成功");
    }

    @Override
    public boolean support(Captcha captcha) {
        return Captcha.SMS==captcha;
    }
}

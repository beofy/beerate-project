package cn.beerate.captcha.mobile;

import cn.beerate.PropertiesHolder;
import cn.beerate.captcha.*;
import cn.beerate.common.Message;
import cn.beerate.exception.ExceptionHandle;
import cn.beerate.utils.StringUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 手机验证码处理器
 */
@Component
public class SmsCaptchaProcessor extends AbstractCaptchaProcessor implements CaptchaProcessor {
    private static final Log logger = LogFactory.getLog(ExceptionHandle.class);
    private ISms chuangLanSms;
    public SmsCaptchaProcessor(ISms chuangLanSms) {
        this.chuangLanSms = chuangLanSms;
    }

    @Override
    public Message<String> create(HttpServletRequest request, HttpServletResponse response, CaptchaScene captchaScene, Captcha captcha){
        Message<String> message = getMobile(request);
        if (message.fail()){
            return message;
        }

        SmsCaptchaCode smsCaptchaCode = (SmsCaptchaCode)request.getSession().getAttribute(getSessionKey(captcha,captchaScene));
        if(smsCaptchaCode!=null&&smsCaptchaCode.getMobile().equals(message.getData())&&smsCaptchaCode.checkExpireIn()){
            return Message.error("短信已发送，请稍候再试");
        }

        SmsCaptchaCode smsCaptchaCode1 = new SmsCaptchaCode(RandomStringUtils.randomNumeric(6),300,message.getData());
        save(request,captcha,captchaScene,smsCaptchaCode1);

        return send(request,response,smsCaptchaCode1);
    }

    @Override
    public Message<String> send(HttpServletRequest request, HttpServletResponse response, CaptchaGenerator captchaGenerator) {
        SmsCaptchaCode smsCaptchaCode = (SmsCaptchaCode)captchaGenerator;
        chuangLanSms.sendSMS(smsCaptchaCode.getMobile(),"验证码：["+captchaGenerator.getCaptchaCode()+"]");
        logger.info(String.format("手机号：%s,验证码：[%s]",smsCaptchaCode.getMobile(),captchaGenerator.getCaptchaCode()));

        //测试返回
        if (PropertiesHolder.properties.getSecurityProperties().isIs_send_test_sms()){
            return Message.ok(String.format("发送成功,手机号：%s,验证码：[%s]",smsCaptchaCode.getMobile(),captchaGenerator.getCaptchaCode()));
        }

        return Message.ok("发送成功");
    }

    @Override
    public Message<String> check(HttpServletRequest request, Captcha captcha, CaptchaScene captchaScene, String captchaCode) {
        SmsCaptchaCode smsCaptchaCode = (SmsCaptchaCode)request.getSession().getAttribute(getSessionKey(captcha,captchaScene));

        //验证码是否存在
        if(smsCaptchaCode==null){
            return Message.error("验证码不存在");
        }

        //手机号是否匹配
        if(!getMobile(request).getData().equalsIgnoreCase(smsCaptchaCode.getMobile())){
            return Message.error("系统异常");
        }

        //验证码判断
        if (!captchaCode.equalsIgnoreCase(smsCaptchaCode.getCaptchaCode())){
            return Message.error("验证码错误");
        }

        //验证码是否过期
        if(!smsCaptchaCode.checkExpireIn()){
            return Message.error("验证码已过期");
        }

        return Message.ok("验证码校验成功");
    }

    @Override
    public boolean support(Captcha captcha) {
        return Captcha.SMS==captcha;
    }

    /**
     * 获取手机号码
     */
    private Message<String> getMobile(HttpServletRequest request){
        String mobile =  request.getParameter("mobile");
        if(!StringUtil.isMobile(mobile)){
            return Message.error("请输入正确的手机号码");
        }

        return Message.success(mobile);
    }


}

package cn.beerate.captcha;

import cn.beerate.common.Message;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class AbstractCaptchaProcessor implements CaptchaProcessor{


    @Override
    public Message<String> create(HttpServletRequest request, HttpServletResponse response, CaptchaScene captchaScene, Captcha captcha) throws IOException {
        //生成验证码
        CaptchaGenerator captchaGenerator = new CaptchaCode(RandomStringUtils.randomNumeric(6),300);

        //保存验证码
        save(request,captcha,captchaScene,captchaGenerator);

        return send(request,response,captchaGenerator);
    }

    /**
     * 由子类重写实现
     */
    @Override
    public abstract Message<String> send(HttpServletRequest request, HttpServletResponse response,CaptchaGenerator captchaGenerator) throws IOException;

    @Override
    public void save(HttpServletRequest request , Captcha captcha , CaptchaScene scene , CaptchaGenerator captchaGenerator) {
        request.getSession().setAttribute(PREFIX+captcha.name()+"_"+scene.name(),captchaGenerator);
    }

    @Override
    public Message<String> check(HttpServletRequest request , Captcha captcha , CaptchaScene scene, String captchaCode) {
        final String CAPTCHA_SESSION_KEY = PREFIX+captcha.name()+"_"+scene.name();

        HttpSession session = request.getSession();

        //获取session储存的验证码
        Object object = session.getAttribute(CAPTCHA_SESSION_KEY);

        if(object==null){
           return Message.error("验证码错误或已超时");
        }

        CaptchaGenerator captchaGenerator = (CaptchaGenerator)object;

        if(!captchaGenerator.getCaptchaCode().equalsIgnoreCase(captchaCode)){
            return  Message.error("验证码错误或已超时");
        }

        //移除验证码
        session.removeAttribute(CAPTCHA_SESSION_KEY);

        return Message.ok("验证码校验成功");
    }

    @Override
    public abstract boolean support(Captcha captcha);
}

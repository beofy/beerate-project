package cn.beerate.captcha;

import cn.beerate.common.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class AbstractCaptchaProcessor implements CaptchaProcessor{

    @Override
    public abstract Message<String> create(HttpServletRequest request, HttpServletResponse response, CaptchaScene captchaScene, Captcha captcha) throws IOException ;

    @Override
    public abstract Message<String> send(HttpServletRequest request, HttpServletResponse response,CaptchaGenerator captchaGenerator) throws IOException ;

    @Override
    public void save(HttpServletRequest request , Captcha captcha , CaptchaScene captchaScene , CaptchaGenerator captchaGenerator) {
        request.getSession().setAttribute(getSessionKey(captcha,captchaScene),captchaGenerator);
    }

    @Override
    public abstract Message<String> check(HttpServletRequest request , Captcha captcha , CaptchaScene scene, String captchaCode);

    @Override
    public void remove(HttpSession session, Captcha captcha, CaptchaScene scene) {
        session.removeAttribute(PREFIX+captcha.name()+"_"+scene.name());
    }

    protected String getSessionKey( Captcha captcha, CaptchaScene scene){
       return PREFIX+captcha.name()+"_"+scene.name();
    }

}

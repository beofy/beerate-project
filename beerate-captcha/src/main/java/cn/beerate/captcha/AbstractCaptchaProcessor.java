package cn.beerate.captcha;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class AbstractCaptchaProcessor implements CaptchaProcessor{


    @Override
    public void create(ServletWebRequest servletWebRequest,CaptchaType captchaType) throws IOException {
        CaptchaGenerator captchaGenerator = captchaType.getCaptchaGenerator(RandomStringUtils.randomNumeric(6),300);
        save(servletWebRequest,"CAPTCHA_"+captchaType.getType().toUpperCase(),captchaGenerator);
        send(servletWebRequest,captchaGenerator);
    }

    /**
     * 由子类重写实现
     * @param servletWebRequest
     * @param captchaGenerator
     */
    @Override
    public abstract void send(ServletWebRequest servletWebRequest, CaptchaGenerator captchaGenerator) throws IOException;

    /**
     * 保存验证码
     */
    @Override
    public void save(ServletWebRequest servletWebRequest,String captchaKey,CaptchaGenerator captchaGenerator) {
        HttpSession httpSession = servletWebRequest.getRequest().getSession();
        httpSession.setAttribute(captchaKey,captchaGenerator);
    }

    @Override
    public void remove(ServletWebRequest servletWebRequest) {

    }

    @Override
    public boolean check(ServletWebRequest servletWebRequest) {
        return false;
    }
}

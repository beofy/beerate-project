package cn.beerate.captcha;

import cn.beerate.common.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 验证码处理器
 */
public interface CaptchaProcessor {

    String PREFIX= "CAPTCHA_";

    /**
     * 创建验证码
     *
     */
    Message<String> create(HttpServletRequest request, HttpServletResponse response, CaptchaScene scene, Captcha captcha) throws IOException;

    /**
     * 保存验证码
     */
    void save(HttpServletRequest request, Captcha captcha , CaptchaScene scene , CaptchaGenerator captchaGenerator);

    /**
     * 发送验证码
     */
    Message<String> send(HttpServletRequest request, HttpServletResponse response,CaptchaGenerator captchaGenerator) throws IOException;

    /**
     * 验证码校验
     */
    Message<String> check(HttpServletRequest request,Captcha captcha, CaptchaScene scene, String captchaCode);

    /**
     * 移除验证码
     */
    void remove(HttpSession session,Captcha captcha, CaptchaScene scene);

    /**
     * 是否支持当前类型的处理器
     */
    boolean support(Captcha captcha);

}

package cn.beerate.captcha;

import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * 验证码处理器
 */
public interface CaptchaProcessor {

    /**
     * 创建验证码
     */
    void create(ServletWebRequest servletWebRequest,CaptchaType captchaType) throws IOException;

    /**
     * 发送验证码
     */

    void send(ServletWebRequest servletWebRequest, CaptchaGenerator captchaGenerator) throws IOException;

    /**
     * 保存验证码
     */
    void save(ServletWebRequest servletWebRequest,String captchaKey,CaptchaGenerator captchaGenerator);

    /**
     * 删除验证码
     */
    void remove(ServletWebRequest servletWebRequest);

    /**
     * 验证码校验
     */
    boolean check(ServletWebRequest servletWebRequest);

}

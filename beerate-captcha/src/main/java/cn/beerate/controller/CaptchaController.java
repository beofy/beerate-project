package cn.beerate.controller;

import cn.beerate.captcha.CaptchaGenerator;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.captcha.CaptchaType;
import cn.beerate.captcha.image.ImageCaptchaProcessor;
import cn.beerate.common.Message;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码控制
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @GetMapping("/{validate}")
    public void validate(@PathVariable String validate, HttpServletRequest request, HttpServletResponse response) throws IOException{
        CaptchaType captchaType = CaptchaType.getCaptchaType(validate);
        Assert.notNull(captchaType,"验证码类型错误！");

        //包装请求
        ServletWebRequest webRequest = new ServletWebRequest(request,response);

        //获取验证码处理
        CaptchaProcessor captchaProcessor = captchaType.getCaptchaProcessor();

        //创建验证码
        captchaProcessor.create(webRequest,captchaType);

    }

}

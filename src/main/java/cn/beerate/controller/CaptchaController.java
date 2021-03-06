package cn.beerate.controller;

import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.captcha.CaptchaProcessorHolder;
import cn.beerate.captcha.CaptchaScene;
import cn.beerate.common.Message;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码控制
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController extends BaseController{

    /**
     * 验证码处理容器
     */
    private CaptchaProcessorHolder captchaProcessorHolder;
    public CaptchaController(CaptchaProcessorHolder captchaProcessorHolder) {
        this.captchaProcessorHolder = captchaProcessorHolder;
    }

    @GetMapping("/{captcha}/{scene}")
    public Message<String> create(@PathVariable String captcha, @PathVariable String scene, HttpServletResponse response) throws IOException {

        Captcha captchaEum = EnumUtils.getEnumIgnoreCase(Captcha.class,captcha);
        Assert.notNull(captchaEum, "验证码类型错误！");

        CaptchaScene sceneEum = EnumUtils.getEnumIgnoreCase(CaptchaScene.class,scene);
        Assert.notNull(sceneEum, "验证码场景类型错误！");

        //获取验证码处理器
        CaptchaProcessor captchaProcessor = captchaProcessorHolder.getCaptchaProcessor(captchaEum);
        Assert.notNull(captchaProcessor, "验证码处理器未找到！");

        //创建验证码
        return captchaProcessor.create(super.getRequest(), response,sceneEum ,captchaEum);
    }

//    @PostMapping("/{captcha}/{scene}")
//    public Message check(@PathVariable String captcha, @PathVariable String scene, @Param("captchaCode") String captchaCode){
//
//        Captcha captchaEum = EnumUtils.getEnumIgnoreCase(Captcha.class,captcha);
//        Assert.notNull(captchaEum, "验证码类型错误");
//
//        CaptchaScene captchaScene = EnumUtils.getEnumIgnoreCase(CaptchaScene.class,scene);
//        Assert.notNull(captchaScene, "验证码场景类型错误");
//
//        Assert.notNull(captchaCode, "验证码错误");
//
//        //获取验证码处理
//        CaptchaProcessor captchaProcessor = captchaProcessorHolder.getCaptchaProcessor(captchaEum);
//        Assert.notNull(captchaProcessor, "验证码处理器未找到！");
//
//        return captchaProcessor.check(super.getRequest(),captchaEum,captchaScene,captchaCode);
//    }

}

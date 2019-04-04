package cn.beerate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

/**
 * 验证码控制
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @GetMapping("/{validate}")
    public Message validate(@PathVariable String validate){
        return null;
    }

}

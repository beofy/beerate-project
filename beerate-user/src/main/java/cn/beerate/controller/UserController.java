package cn.beerate.controller;

import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.captcha.CaptchaScene;
import cn.beerate.common.Message;
import cn.beerate.constant.SessionKey;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController extends UserBaseController{

    private UserService userService;
    private CaptchaProcessor smsCatpchaProcessor;
    public UserController(UserService userService, CaptchaProcessor smsCatpchaProcessor) {
        this.userService = userService;
        this.smsCatpchaProcessor = smsCatpchaProcessor;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Message<String> login(){
        return null;
    }

    /**
     * 注册
     */
    @PostMapping("/registe")
    public Message<String> registe(String mobile ,String password,String smsCaptchaCode){
        if(StringUtils.isBlank(mobile)){
            return Message.error("请输入手机号码");
        }

        if(StringUtils.isBlank(password)){
            return Message.error("请输入密码");
        }

        if(StringUtils.isBlank(smsCaptchaCode)){
            return Message.error("请输入验证码");
        }

        //短信验证码校验
        Message<String> messageCaptchaCheck = smsCatpchaProcessor.check(getRequest(), Captcha.SMS, CaptchaScene.USER_REGIST,smsCaptchaCode);
        if(messageCaptchaCheck.fail()){
            return messageCaptchaCheck;
        }
        //清空验证码
        smsCatpchaProcessor.remove(getSession(),Captcha.SMS, CaptchaScene.USER_REGIST);

        //注册
        Message<t_user> message = userService.registe(mobile,password,getIp(),getChannel().name());
        if(message.fail()){
            return Message.error(message.getMsg());
        }

        //保存登录状态
        super.getSession().setAttribute(SessionKey.USER_SESSION_KEY,message.getData());

        return Message.ok("注册成功");
    }

    /**
     * 退出登录
     */
    @GetMapping("/loginout")
    public Message<String> loginout(){
        return null;
    }

}

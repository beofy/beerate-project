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

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController extends UserBaseController{

    private UserService userService;
    private CaptchaProcessor smsCatpchaProcessor;
    private CaptchaProcessor imageCaptchaProcessor;

    public UserController(UserService userService, CaptchaProcessor smsCatpchaProcessor, CaptchaProcessor imageCaptchaProcessor) {
        this.userService = userService;
        this.smsCatpchaProcessor = smsCatpchaProcessor;
        this.imageCaptchaProcessor = imageCaptchaProcessor;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Message<String> login(String mobile ,String password,String imageCaptchaCode){
        if(StringUtils.isBlank(mobile)){
            return Message.error("请输入手机号");
        }

        if(StringUtils.isBlank(password)){
            return Message.error("请输入密码");
        }

        //处理验证码
        @SuppressWarnings("unchecked")
        Map<String,Integer> failCount = (Map<String,Integer>)getSession().getAttribute(SessionKey.USER_LOGIN_FAIL_COUNT);

        if(failCount!=null&&failCount.get(mobile)!=null&&failCount.get(mobile)>3){
            if(StringUtils.isBlank(imageCaptchaCode)){
                return Message.error("请输入验证码");
            }

            Message<String> messageCaptcha = imageCaptchaProcessor.check(getRequest(),Captcha.IMAGE,CaptchaScene.USER_LOGIN,imageCaptchaCode);
            if (messageCaptcha.fail()){
                return messageCaptcha;
            }
        }

        //执行登录
        Message<t_user> message = userService.login(mobile,password,getIp(),getChannel().name());
        if(message.fail()){
            //增加错误登录次数
            if(failCount==null){
                failCount= new HashMap<>();
                failCount.put(mobile,1);
            }else {
                if(failCount.get(mobile)==null){
                    failCount.put(mobile,1);
                }else {
                    failCount.put(mobile,failCount.get(mobile)+1);
                }
            }

            getSession().setAttribute(SessionKey.USER_LOGIN_FAIL_COUNT,failCount);
            return Message.error(message.getMsg());
        }

        //保存登录状态
        super.getSession().setAttribute(SessionKey.USER_SESSION_KEY,message.getData());

        return Message.ok("登录成功");
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
        getSession().removeAttribute(SessionKey.USER_SESSION_KEY);
        return Message.ok("登出成功");
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/updateUserInfo")
    public Message<String> updateUserInfo(){

        return Message.ok("更新用户信息成功");
    }

    /**
     * 更新名片
     */
    @PostMapping("/updateUserBusiness")
    public Message<String> updateUserBusiness(String businessFilePath){


        return Message.ok("");
    }

}

package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.captcha.CaptchaScene;
import cn.beerate.common.IToken;
import cn.beerate.common.Message;
import cn.beerate.common.StatusCode;
import cn.beerate.constant.SessionKey;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.UserService;
import cn.beerate.utils.PathUtil;
import cn.beerate.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController extends UserBaseController{
    private final Log logger = LogFactory.getLog(this.getClass());
    private UserService userService;
    private CaptchaProcessor smsCaptchaProcessor;
    private CaptchaProcessor imageCaptchaProcessor;
    private CaptchaProcessor emailCaptchaProcessor;

    public UserController(UserService userService, CaptchaProcessor smsCaptchaProcessor, CaptchaProcessor imageCaptchaProcessor, CaptchaProcessor emailCaptchaProcessor) {
        this.userService = userService;
        this.smsCaptchaProcessor = smsCaptchaProcessor;
        this.imageCaptchaProcessor = imageCaptchaProcessor;
        this.emailCaptchaProcessor = emailCaptchaProcessor;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Message<IToken> login(String mobile ,String password,String imageCaptchaCode){
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
            imageCaptchaProcessor.remove(getSession(),Captcha.IMAGE,CaptchaScene.USER_LOGIN);
            if (messageCaptcha.fail()){
                return Message.error(messageCaptcha.getMsg());
            }
        }

        //执行登录
        Message<t_user> message = userService.login(mobile,password,getIp(),getChannel().name(),getSession().getId());
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
        setUserSession(message.getData());

        return new Message<>(StatusCode.SUCCESS,"登录成功",getIToken(getUserId()));
    }

    /**
     * 注册
     */
    @PostMapping("/reg")
    public Message<IToken> reg(String mobile ,String password,String smsCaptchaCode){
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
        Message<String> messageCaptchaCheck = smsCaptchaProcessor.check(getRequest(), Captcha.SMS, CaptchaScene.USER_REGIST,smsCaptchaCode);
        if(messageCaptchaCheck.fail()){
            return Message.error(messageCaptchaCheck.getMsg());
        }
        //清空验证码
        smsCaptchaProcessor.remove(getSession(),Captcha.SMS, CaptchaScene.USER_REGIST);

        //注册
        Message<t_user> message = userService.registe(mobile,password,getIp(),getChannel().name(),getSession().getId());
        if(message.fail()){
            return Message.error(message.getMsg());
        }

        //保存登录状态
        setUserSession(message.getData());

        return new Message<>(StatusCode.SUCCESS,"注册成功",getIToken(getUserId()));
    }

    /**
     * 退出登录
     */
    @GetMapping("/loginOut")
    public Message<String> loginOut(){
        removeUserSession();
        return Message.ok("登出成功");
    }

    /**
     * 更新用户头像
     */
    @PostMapping("/updateUserPhoto")
    public Message<String> updateUserPhoto(MultipartFile file){
        Message<t_user> message = userService.updateUserPhoto(PropertiesHolder.ATTACHMENT_PATH+ StringUtil.generateFileName(file.getOriginalFilename()),getUserId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        //更新用户头像
        getUser().setPhoto(message.getData().getPhoto());

        try {
            file.transferTo(new File(PathUtil.getRoot()+message.getData().getPhoto()));
        } catch (IOException e) {
            logger.error(String.format("用户头像保存失败,原因：[%s]",e.getCause()),e);
            return Message.error("上传失败");
        }

        return Message.ok("更新成功");
    }

    /**
     * 更新用户密码
     */
    @PostMapping("/updateUserPassWord")
    public Message<String> updateUserPassWord(String oldPwd,String newPwd){
        Message<t_user> message =  userService.updateUserPassWord(oldPwd, newPwd, getUserId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("密码更新成功");
    }

    /**
     * 更新用户手机
     */
    @PostMapping("/updateUserMobile")
    public Message<String> updateUserMobile(String mobile,String smsCaptchaCode){
        //验证码校验
        Message<String> messageCaptchaCheck = smsCaptchaProcessor.check(getRequest(),Captcha.SMS,CaptchaScene.UPDATE_USER_MOBILE,smsCaptchaCode);
        if(messageCaptchaCheck.fail()){
            return Message.error(messageCaptchaCheck.getMsg());
        }
        //移除验证码
        smsCaptchaProcessor.remove(getSession(),Captcha.SMS,CaptchaScene.UPDATE_USER_MOBILE);

        Message<t_user> message =  userService.updateUserMobile(mobile, getUserId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        //更新用户手机
        getUser().setMobile(message.getData().getMobile());

        return Message.ok("手机更新成功");
    }

    /**
     * 更新用户邮箱
     */
    @PostMapping("/updateUserEmail")
    public Message<String> updateUserEmail(String email,String emailCaptchaCode){
        //验证码校验
        Message<String> messageCaptchaCheck = emailCaptchaProcessor.check(getRequest(),Captcha.EMAIL,CaptchaScene.UPDATE_USER_EMAIL,emailCaptchaCode);
        if(messageCaptchaCheck.fail()){
            return Message.error(messageCaptchaCheck.getMsg());
        }
        //移除验证码
        emailCaptchaProcessor.remove(getSession(),Captcha.EMAIL,CaptchaScene.UPDATE_USER_EMAIL);

        Message<t_user> message =  userService.updateUserEmail(email, getUserId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        //更新用户邮箱
        getUser().setEmail(message.getData().getEmail());

        return Message.ok("邮箱更新成功");
    }

    /**
     * 重置用户密码
     */
    @PostMapping("/resetUserPassWord")
    public Message<String> resetUserPassWord(String mobile,String newPwd,String smsCaptchaCode){
        //验证码校验
        Message<String> messageCaptchaCheck = smsCaptchaProcessor.check(getRequest(),Captcha.SMS,CaptchaScene.RESET_USER_PASSWORD,smsCaptchaCode);
        if(messageCaptchaCheck.fail()){
            return Message.error(messageCaptchaCheck.getMsg());
        }
        //移除验证码
        smsCaptchaProcessor.remove(getSession(),Captcha.SMS,CaptchaScene.RESET_USER_PASSWORD);

        Message<t_user> message =  userService.resetUserPassword(mobile, newPwd);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("密码重置成功");
    }

    /**
     * 查询当前用户信息
     */
    @PostMapping("/me")
    public Message me(){
        return Message.success(getUser());
    }
}

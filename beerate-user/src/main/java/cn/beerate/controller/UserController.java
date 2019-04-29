package cn.beerate.controller;

import cn.beerate.utils.PathUtil;
import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.captcha.CaptchaScene;
import cn.beerate.common.Message;
import cn.beerate.constant.SessionKey;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.bean.User;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.UserBusinessService;
import cn.beerate.service.UserInfoService;
import cn.beerate.service.UserService;
import org.apache.commons.lang3.EnumUtils;
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
    private UserInfoService userInfoService;
    private UserBusinessService userBusinessService;
    private CaptchaProcessor smsCatpchaProcessor;
    private CaptchaProcessor imageCaptchaProcessor;

    public UserController(UserService userService, UserInfoService userInfoService, UserBusinessService userBusinessService, CaptchaProcessor smsCatpchaProcessor, CaptchaProcessor imageCaptchaProcessor) {
        this.userService = userService;
        this.userInfoService = userInfoService;
        this.userBusinessService = userBusinessService;
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


        t_user user = message.getData();

        //检查认证状态
        boolean isApprove=false;
        if(user.getUser_business()!=null&&EnumUtils.getEnumIgnoreCase(AuditStatus.class,user.getUser_business().getAuditStatus())==AuditStatus.PASS_AUDIT){
            isApprove=true;
        }
        User currUser = new User(user.getId(),user.getUsername(),user.getPhoto(),isApprove);

        //保存登录状态
        super.getSession().setAttribute(SessionKey.USER_SESSION_KEY,currUser);

        return Message.ok("登录成功");
    }

    /**
     * 注册
     */
    @PostMapping("/reg")
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
    @GetMapping("/loginOut")
    public Message<String> loginOut(){
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
     * 查询当前用户信息
     */

    @PostMapping("/me")
    public Message me(){
        t_user user =userService.getOne(getUserId());

        return Message.success(user.getUser_business());
    }


    /**
     * 上传用户名片
     */
    @PostMapping("/uploadUserBusiness")
    public Message<String> uploadUserBusiness(String businessFilePath){
        if(StringUtils.isBlank(businessFilePath)){
            return Message.success("请上传名片");
        }

        //临时文件资源
        String tempFilePath = PathUtil.getTempPath()+businessFilePath;
        //保存用户资源
        String userFilePath = PathUtil.getUserPath()+businessFilePath;

        return userBusinessService.uploadUserBusiness(tempFilePath,userFilePath,getUserId());
    }

    /**
     * 更新名片信息
     */
    @PostMapping("/updateUserBusiness")
    public Message<String> updateUserBusiness(){


        return Message.ok("");
    }
}

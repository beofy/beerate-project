package cn.beerate.controller;

import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.captcha.CaptchaScene;
import cn.beerate.common.Message;
import cn.beerate.common.StatusCode;
import cn.beerate.constant.SessionKey;
import cn.beerate.model.entity.t_admin;
import cn.beerate.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 管理员控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends AdminBaseController{


    private AdminService adminService;
    private CaptchaProcessor imageCaptchaProcessor;

    public AdminController(AdminService adminService, CaptchaProcessor imageCaptchaProcessor) {
        this.adminService = adminService;
        this.imageCaptchaProcessor = imageCaptchaProcessor;
    }

    /**
     * 登录页面
     */
    @GetMapping("/login.html")
    public String login(){
        return "admin/login";
    }

    /**
     * 主页
     */
    @GetMapping("/main.html")
    public String main(){
        return "admin/main";
    }


    /**
     * 查询管理员信息
     */
    @GetMapping("/me.html")
    public String me(Model model){
        model.addAttribute("admin",adminService.getOne(getAdminId()));
        return "admin/me";
    }

    /**
     * 管理员列表
     */
    @GetMapping("/list.html")
    public String list(int page, int size,
                           @RequestParam(required = false) String column,
                           @RequestParam(required = false) String order,
                           @RequestParam(required = false) String field,
                           @RequestParam(required = false) String value,
                           @RequestParam(required = false) Date beginDate,
                           @RequestParam(required = false) Date endDate,
                           Model model){

        model.addAttribute("page",adminService.page(page, size, column, order,field,value,beginDate,endDate));
        return "admin/list";
    }



    /**
     * 密码修改页面
     */
    @GetMapping("/updatePassWord.html")
    public String updatePassWordPage(){
        return "admin/updatePassWord";
    }

    /**
     * 密码修改
     */
    @PostMapping("/updatePassWord")
    @ResponseBody
    public Message<String> updatePassWord(String oldPwd,String newPwd){
       Message<t_admin> message = adminService.updateAdminPassWord(oldPwd,newPwd,getAdminId());
       if (message.fail()){
           return Message.error(message.getMsg());
       }

        //登出
        super.signOut();

       return Message.ok("密码修改成功");
    }


    /**
     * 管理员添加页面
     */
    @GetMapping("add.html")
    public String addPage(){
        return "admin/add";
    }


    /**
     * 添加管理员
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_admin admin){

        if(admin==null){
            return Message.error("系统异常");
        }

        if(StringUtils.isBlank(admin.getUsername())){
            return Message.error("账号不能为空");
        }

        if(StringUtils.isBlank(admin.getPassword())){
            return Message.error("密码不能为空");
        }

        if(StringUtils.isBlank(admin.getMobile())){
            return Message.error("手机不能为空");
        }

        if(StringUtils.isBlank(admin.getEmail())){
            return Message.error("邮箱不能为空");
        }

        if(StringUtils.isBlank(admin.getRealityName())){
            return Message.error("真实姓名不能为空");
        }

        if(StringUtils.isBlank(admin.getDepartment())){
            return Message.error("部门不能为空");
        }

        if(StringUtils.isBlank(admin.getPosition())){
            return  Message.error("职位不能为空");
        }

        Message<t_admin> message =adminService.addAdmin(admin,getAdminId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加管理员成功");
    }


    /**
     * 管理员登录
     */
    @PostMapping("/login")
    @ResponseBody
    public Message<String> login(String username, String password , String captchaCode){

        if(StringUtils.isBlank(username)){
            return Message.error("请输入用户名");
        }

        if(StringUtils.isBlank(password)){
            return Message.error("请输入密码");
        }

        if(StringUtils.isBlank(captchaCode)){
            return Message.error("请输入验证码");
        }

        //处理验证码
        Message<String> messageCaptchaCheck = imageCaptchaProcessor.check(getRequest(), Captcha.IMAGE, CaptchaScene.ADMIN_LOGIN,captchaCode);
        imageCaptchaProcessor.remove(getSession(),Captcha.IMAGE,CaptchaScene.ADMIN_LOGIN);
        if(messageCaptchaCheck.fail()){
            return messageCaptchaCheck;
        }

        //检查账号密码
        Message<t_admin> messageLogin =  adminService.login(username, password,getRequest().getRemoteHost());
        if(messageLogin.fail()){
            return Message.error(messageLogin.getMsg());
        }

        //保存登录状态
        getSession().setAttribute(SessionKey.ADMIN_SESSION_KEY,messageLogin.getData());

        return new Message<>(StatusCode.SUCCESS,"登录成功",messageLogin.getData().getId().toString());
    }


    @GetMapping("/update.html")
    public String update(long adminId,Model model){
        model.addAttribute("admin",adminService.getOne(adminId));

        return "admin/update";
    }

    @PostMapping("/update")
    @ResponseBody
    public Message<String> update(t_admin admin,long adminId){
        Message<t_admin> message = adminService.updateAdmin(admin,adminId);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("修改成功");
    }

    @PostMapping("/updateLock")
    @ResponseBody
    public Message<String> updateLock(long adminId){
        Message<t_admin> message = adminService.updateLock(adminId);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("设置成功");
    }

    /**
     * 登出
     */
    @GetMapping("/loginOut")
    public String loginOut(){
        super.signOut();
        return "redirect:/admin/login.html";
    }

}

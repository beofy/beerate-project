package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.captcha.CaptchaScene;
import cn.beerate.common.Message;
import cn.beerate.common.StatusCode;
import cn.beerate.constant.SessionKey;
import cn.beerate.model.entity.t_admin;
import cn.beerate.oss.OSS;
import cn.beerate.oss.OssObject;
import cn.beerate.oss.OssObjectUtil;
import cn.beerate.security.Encrypt;
import cn.beerate.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 管理员控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends AdminBaseController{


    private AdminService adminService;
    private CaptchaProcessor imageCaptchaProcessor;
    private OSS oss;

    public AdminController(AdminService adminService, CaptchaProcessor imageCaptchaProcessor, OSS oss) {
        this.adminService = adminService;
        this.imageCaptchaProcessor = imageCaptchaProcessor;
        this.oss = oss;
    }

    /**
     * 登录页面
     */
    @GetMapping("/login.html")
    public String loginPage(){
        return "admin/login";
    }


    /**
     * 主页
     */
    @GetMapping("/main.html")
    public String mainPage(){
        return "admin/main";
    }


    /**
     * 查询管理员信息
     */
    @GetMapping("/me.html")
    public String mePage(Model model){
        model.addAttribute("admin",adminService.getOne(getAdminId()));
        return "admin/me";
    }


    /**
     * 管理员列表
     */
    @GetMapping("/list.html")
    public String listPage(int page, int size, String column, String order, Date beginDate, Date endDate, t_admin admin , Model model){

        Page<t_admin> pageBean = adminService.page(page, size, column, order, new Specification<t_admin>() {
            @Override
            public Predicate toPredicate(Root<t_admin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(beginDate!=null&&endDate!=null){
                    predicates.add(criteriaBuilder.between(root.get("createTime") , beginDate, endDate));
                }

                if(StringUtils.isNotBlank(admin.getUsername())){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("username"),"%"+admin.getUsername()+"%")));
                }


                Predicate[] predicate = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(predicate));
            }
        });

        model.addAttribute("page",pageBean);

        return "admin/list";
    }

//    public String listPage(int page,int size,String colum,String order,t_admin admin ,Model model){
//        //匹配条件
//        ExampleMatcher matcher  =ExampleMatcher.matching()
//                .withIgnoreNullValues()//忽略空值
//                //采用模糊匹配
//                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("realityName", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("mobile", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("department", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("position", ExampleMatcher.GenericPropertyMatchers.contains());
//
//        Example<t_admin> example =  Example.of(admin,matcher);
//
//        Page<t_admin> pageBean = adminService.page(page,size,colum,order,example);
//
//        model.addAttribute("page",pageBean);
//
//        return "admin/list";
//    }


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
    public Message<String> updatePassWord(String oldPassWod,String newPassWord){
        t_admin admin = adminService.getOne(getAdminId());
        String encOldPassWod = Encrypt.MD5(oldPassWod+ PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt());
        if(!admin.getPassword().equals(encOldPassWod)){
            return Message.error("密码错误");
        }

        //更新密码
        String encNwePassWord = Encrypt.MD5(newPassWord+ PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt());
        admin.setPassword(encNwePassWord);
        adminService.save(admin);

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

        //处理头像
        if(StringUtils.isNotBlank(admin.getPhoto())){
            OssObject ossObject = OssObjectUtil.validate(admin.getPhoto(),getSession().getId());
            String adminPhoto = PropertiesHolder.properties.getFileProperties().getAdminFile()+ossObject.getFileName();
            try {
                oss.uploadFile(new FileInputStream(new File(ossObject.getUrl())),oss.getRoot()+adminPhoto);
            } catch (IOException e) {
                return  Message.error("系统异常");
            }

            //设置头像路径
            admin.setPhoto("/"+adminPhoto);
        }



        return adminService.addAdmin(admin,getAdminId());
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


    /**
     * 登出
     */
    @GetMapping("/loginout")
    public String loginOut(){
        super.getSession().removeAttribute(SessionKey.ADMIN_SESSION_KEY);

        return "redirect:/admin/login.html";
    }



}

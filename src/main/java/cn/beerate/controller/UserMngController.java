package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ItemType;
import cn.beerate.model.entity.t_user_business;
import cn.beerate.service.UserBusinessService;
import cn.beerate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 用户管理控制器
 */
@RequestMapping("/admin/user")
@Controller
public class UserMngController extends AdminBaseController{

    private UserService userService;
    private UserBusinessService userBusinessService;

    public UserMngController(UserService userService, UserBusinessService userBusinessService) {
        this.userService = userService;
        this.userBusinessService = userBusinessService;
    }

    @RequestMapping("/list.html")
    public String list(int page, int size,
                       @RequestParam(required = false) String column,
                       @RequestParam(required = false) String order,
                       @RequestParam(required = false) String field,
                       @RequestParam(required = false) String value,
                       @RequestParam(required = false) Date beginDate,
                       @RequestParam(required = false) Date endDate,
                       Model model){

        model.addAttribute("page", userService.page(page,size,column,order,field,value,beginDate,endDate));

        return "admin/user/list";
    }

    @RequestMapping("/detail.html")
    public String detail(long userId,Model model){
        model.addAttribute("user",userService.getOne(userId));
        model.addAttribute("itemTypes", ItemType.values());
        model.addAttribute("auditStatus", AuditStatus.values());
        return "admin/user/detail";
    }

    @PostMapping("/update")
    @ResponseBody
    public Message<String> update(t_user_business business,long userId){
       Message<t_user_business> message = userBusinessService.updateBusinessByUserId(business,userId);
       if (message.fail()){
           return Message.error(message.getMsg());
       }

       return Message.ok();
    }

}

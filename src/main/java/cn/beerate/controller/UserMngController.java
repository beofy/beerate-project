package cn.beerate.controller;

import cn.beerate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 用户管理控制器
 */
@RequestMapping("/admin/user")
@Controller
public class UserMngController extends AdminBaseController{

    private UserService userService;
    public UserMngController(UserService userService) {
        this.userService = userService;
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

}

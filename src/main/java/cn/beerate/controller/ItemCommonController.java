package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.InvestPrefer;
import cn.beerate.model.ItemModel;
import cn.beerate.service.AdminService;
import cn.beerate.service.ItemCommonService;
import cn.beerate.service.UserService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/admin/item/common")
public class ItemCommonController extends AdminBaseController{

    private AdminService adminService;
    private UserService userService;
    private Map<String,ItemCommonService> itemCommonService;

    @Autowired
    public ItemCommonController(AdminService adminService, UserService userService, Map<String, ItemCommonService> itemCommonService) {
        this.adminService = adminService;
        this.userService = userService;
        this.itemCommonService = itemCommonService;
    }

    /**
     * 公共管理员信息
     */
    @PostMapping("/adminInfo.html")
    public String AdminInfo(long adminId,Model model){
        model.addAttribute("admin",adminService.getOne(adminId));
        return "admin/item/common/adminInfo";
    }

    /**
     * 公共的用户信息
     */
    @PostMapping("/businessInfo.html")
    public String businessInfo(long userId,Model model){
        model.addAttribute("user",userService.getOne(userId));
        model.addAttribute("investPrefers", InvestPrefer.values());
        model.addAttribute("auditStatus", AuditStatus.values());

        return "admin/item/common/businessInfo";
    }

    /**
     * 分配人列表
     */
    @PostMapping("/assigner.html")
    public String assigner(int page, int size,
                           String serviceName,
                           long itemId,
                           @RequestParam(required = false) String column,
                           @RequestParam(required = false) String order,
                           @RequestParam(required = false) String field,
                           @RequestParam(required = false) String value,
                           @RequestParam(required = false) Date beginDate,
                           @RequestParam(required = false) Date endDate,
                           Model model){
        model.addAttribute("page",adminService.page(page, size, column, order,field,value,beginDate,endDate));
        model.addAttribute("serviceName",serviceName);
        model.addAttribute("itemId",itemId);

        return "admin/item/common/assigner";
    }

    /**
     * 项目分配
     */
    @PostMapping("/assigner")
    @ResponseBody
    public Message<String> assigner(String serviceName,long adminId,long itemId){
        @SuppressWarnings("unchecked")
        Message<ItemModel> message = itemCommonService.get(serviceName).addItemAssigner(adminId,itemId);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("分配成功");
    }

    /**
     * 项目审核
     */
    @PostMapping("/audit")
    @ResponseBody
    public Message<String> detail(String serviceName,String auditStatus, String description, long itemId){
        @SuppressWarnings("unchecked")
        Message<ItemModel> message =itemCommonService.get(serviceName).auditItem(EnumUtils.getEnumIgnoreCase(AuditStatus.class,auditStatus),description,itemId);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("审核成功");
    }


}

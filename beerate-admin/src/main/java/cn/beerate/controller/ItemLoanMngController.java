package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.ItemLoanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台项目融资管理-控制器
 */
@Controller
@RequestMapping("/admin/itemloan")
public class ItemLoanMngController extends AdminBaseController {

    private ItemLoanService itemLoanService;

    public ItemLoanMngController(ItemLoanService itemLoanService) {
        this.itemLoanService = itemLoanService;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(){

        return "itemloan/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(){
        return "itemloan/add";
    }

    /**
     * 项目添加
     */
    public Message<String> add(t_item_loan itemLoan){
        Message<t_item_loan> message = itemLoanService.addItemLoanByAdmin(itemLoan,getAdminId());
        if(message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

}

package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.ItemLoanService;
import org.springframework.stereotype.Controller;
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

    public String addPage(){
        return "";
    }

    public Message<String> add(t_item_loan itemLoan){



        return Message.ok("添加成功");
    }

}

package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_stock_pledge;
import cn.beerate.service.StockPledgeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台股票质押管理-控制器
 */
@Controller
@RequestMapping("/admin/item/stockpledge")
public class StockPledgeMngController  extends AdminBaseController {

    private StockPledgeService stockPledgeService;
    public StockPledgeMngController(StockPledgeService stockPledgeService) {
        this.stockPledgeService = stockPledgeService;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(){

        return "admin/item/stockpledge/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(){
        return "admin/item/stockpledge/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_stock_pledge stockPledge){

        Message<t_item_stock_pledge> message = stockPledgeService.addItemByAdmin(stockPledge,getAdminId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

}

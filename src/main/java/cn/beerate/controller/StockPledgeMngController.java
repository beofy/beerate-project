package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.entity.t_item_stock_pledge;
import cn.beerate.service.StockPledgeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public String list(int page, int size,
                       @RequestParam(required = false) String column,
                       @RequestParam(required = false) String order,
                       @RequestParam(required = false) String field,
                       @RequestParam(required = false) String value,
                       @RequestParam(required = false) Date beginDate,
                       @RequestParam(required = false) Date endDate,
                       Model model){
        model.addAttribute("page",stockPledgeService.page(page, size, column, order, field,value,beginDate,endDate));

        return "admin/item/stockpledge/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String add(){
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


    /**
     * 项目详情
     */
    @GetMapping("/detail.html")
    public String detail(long stockPledgeId,Model model){
        model.addAttribute("stockPledge",stockPledgeService.getOne(stockPledgeId));
        model.addAttribute("auditStatus", AuditStatus.values());

        return "admin/item/stockpledge/detail";
    }

}

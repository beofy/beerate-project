package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.entity.t_item_stock_transfer;
import cn.beerate.service.StockTransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 后台老股转让管理-控制器
 */
@Controller
@RequestMapping("/admin/item/stocktransfer")
public class StockTransferMngController extends AdminBaseController  {
    private StockTransferService stockTransferService;

    public StockTransferMngController(StockTransferService stockTransferService) {
        this.stockTransferService = stockTransferService;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(int page, int size,
                           @RequestParam(required = false) String column,
                           @RequestParam(required = false) String order,
                           @RequestParam(required = false) String field,
                           @RequestParam(required = false) String value,
                           @RequestParam(required = false) Date beginDate,
                           @RequestParam(required = false) Date endDate,
                           Model model){
        model.addAttribute("page",stockTransferService.page(page, size, column, order,field,value,beginDate,endDate));

        return "admin/item/stocktransfer/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(Model model){
        model.addAttribute("industryRealms", IndustryRealm.values());
        return "admin/item/stocktransfer/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_stock_transfer stockTransfer){
        Message<t_item_stock_transfer> message = stockTransferService.addItemByAdmin(stockTransfer,getAdminId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    /**
     * 项目详情
     */
    @GetMapping("/detail.html")
    public String detail(long stockTransferId,Model model){
        model.addAttribute("stockTransfer",stockTransferService.getOne(stockTransferId));
        model.addAttribute("auditStatus", AuditStatus.values());

        return "admin/item/stocktransfer/detail";
    }

}

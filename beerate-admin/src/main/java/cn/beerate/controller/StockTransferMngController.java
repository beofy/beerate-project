package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_stock_transfer;
import cn.beerate.service.StockTransferSerivce;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台老股转让管理-控制器
 */
@Controller
@RequestMapping("/admin/stocktransfer")
public class StockTransferMngController extends AdminBaseController  {
    private StockTransferSerivce stockTransferSerivce;
    public StockTransferMngController(StockTransferSerivce stockTransferSerivce) {
        this.stockTransferSerivce = stockTransferSerivce;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(){

        return "stocktransfer/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(){
        return "stocktransfer/add";
    }

    /**
     * 项目添加
     */
    public Message<String> add(t_item_stock_transfer stockTransfer){
        Message<t_item_stock_transfer> message = stockTransferSerivce.addStockTransferByAdmin(stockTransfer,getAdminId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

}

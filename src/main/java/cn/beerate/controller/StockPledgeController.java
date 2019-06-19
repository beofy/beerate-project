package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.StockPledgeDetail;
import cn.beerate.model.dto.MyStockPledge;
import cn.beerate.model.entity.t_item_stock_pledge;
import cn.beerate.service.StockPledgeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/item/stockpledge")
public class StockPledgeController extends UserBaseController {
    private StockPledgeService stockPledgeService;

    public StockPledgeController(StockPledgeService stockPledgeService) {
        this.stockPledgeService = stockPledgeService;
    }

    @PostMapping("/add")
    public Message<String> add(t_item_stock_pledge stockPledge) {
        Message message = stockPledgeService.addItemByUser(stockPledge, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @PostMapping("/list")
    public Message<Page<MyStockPledge>> list(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return Message.success(stockPledgeService.pageMyStockPledge(page,size,column,order,getUserId()));
    }


    @PostMapping("/detail")
    public Message<StockPledgeDetail> detail(long stockPledgeId) {
        return Message.success(stockPledgeService.stockPledgeDetailByUser(stockPledgeId,getUserId()));
    }

    @PostMapping("/update")
    public Message<String> update(t_item_stock_pledge stockPledge, long itemId){
        Message<t_item_stock_pledge> message = stockPledgeService.updateItemByUser(stockPledge,itemId,getUserId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("修改成功");
    }
}

package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.StockPledge;
import cn.beerate.model.dto.StockPledgeList;
import cn.beerate.model.entity.t_item_stock_pledge;
import cn.beerate.service.StockPledgeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/stockpledge")
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

    @GetMapping("/list")
    public Message<Page<StockPledgeList>> list(int page, int size, String column, String order) {



        Page<StockPledgeList> pageBean = null;

        return Message.success(pageBean);
    }


    @PostMapping("/detail")
    public Message<StockPledge> detail(long stockPledgeId) {

        StockPledge pledge = null;

        return Message.success(pledge);
    }
}

package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.StockTransfer;
import cn.beerate.model.dto.StockTransferList;
import cn.beerate.model.entity.t_item_stock_transfer;
import cn.beerate.service.StockTransferService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/item/stocktransfer/")
public class StockTransferController extends UserBaseController {
    private StockTransferService stockTransferService;

    public StockTransferController(StockTransferService stockTransferService) {
        this.stockTransferService = stockTransferService;
    }

    @PostMapping("/add")
    public Message<String> add(t_item_stock_transfer stockTransfer) {
        Message<t_item_stock_transfer> message = stockTransferService.addItemByUser(stockTransfer, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @GetMapping("/list")
    public Message<Page<StockTransferList>> list(int page, int size, String column, String order) {

        Page<StockTransferList> pageBean = null;

        return Message.success(pageBean);
    }


    @PostMapping("/detail")
    public Message<StockTransfer> detail(long stockTransferId) {

        StockTransfer transfer = null;

        return Message.success(transfer);
    }

}

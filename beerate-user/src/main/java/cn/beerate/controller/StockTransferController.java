package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.StockTransfer;
import cn.beerate.model.dto.StockTransferList;
import cn.beerate.model.entity.Qt_item_stock_transfer;
import cn.beerate.model.entity.t_item_stock_transfer;
import cn.beerate.service.StockTransferService;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/stocktransfer/")
public class StockTransferController extends UserBaseController {
    private StockTransferService stockTransferService;

    public StockTransferController(StockTransferService stockTransferService) {
        this.stockTransferService = stockTransferService;
    }

    @PostMapping("/add")
    public Message<String> add(t_item_stock_transfer stockTransfer) {
        Message<t_item_stock_transfer> message = stockTransferService.addStockTransferByUser(stockTransfer, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @GetMapping("/list")
    public Message<Page<StockTransferList>> list(int page, int size, String column, String order) {
        Qt_item_stock_transfer stockTransfer = Qt_item_stock_transfer.t_item_stock_transfer;

        Expression<StockTransferList> expression = Projections.constructor(
                StockTransferList.class,
                stockTransfer.id,
                stockTransfer.bidName,
                stockTransfer.isQuoted,
                stockTransfer.industryRealm,
                stockTransfer.transferAmount,
                stockTransfer.auditStatus
        );

        Page<StockTransferList> pageBean = stockTransferService.page(page, size, column, order, expression, stockTransfer.user.id.eq(getUserId()));

        return Message.success(pageBean);
    }


    @PostMapping("/detail")
    public Message<StockTransfer> detail(long stockTransferId) {
        Qt_item_stock_transfer stockTransfer = Qt_item_stock_transfer.t_item_stock_transfer;

        Expression<StockTransfer> expression = Projections.constructor(
                StockTransfer.class,
                stockTransfer.id,
                stockTransfer.bidName,
                stockTransfer.isQuoted,
                stockTransfer.companyNameIsPublic,
                stockTransfer.companyName,
                stockTransfer.industryRealm,
                stockTransfer.currency,
                stockTransfer.lastYearProfits,
                stockTransfer.currentValuation,
                stockTransfer.transferAmount,
                stockTransfer.isPrivacyEquityRatio,
                stockTransfer.equityRatio,
                stockTransfer.investLightSpot,
                stockTransfer.contact,
                stockTransfer.contactMobile,
                stockTransfer.contentDescription,
                stockTransfer.endTime,
                stockTransfer.isUrgent,
                stockTransfer.isPlatformAuthentication,
                stockTransfer.isFirstHandle,
                stockTransfer.auditStatus

        );

        Predicate predicate = stockTransfer.user.id.eq(getUserId()).and(stockTransfer.id.eq(stockTransferId));
        StockTransfer transfer = stockTransferService.getOne(expression, predicate);

        return Message.success(transfer);
    }

}

package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.StockPledge;
import cn.beerate.model.dto.StockPledgeList;
import cn.beerate.model.entity.Qt_item_stock_pledge;
import cn.beerate.model.entity.t_item_stock_pledge;
import cn.beerate.service.StockPledgeService;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
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
        Message message = stockPledgeService.addStockPledgeByUser(stockPledge, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @GetMapping("/list")
    public Message<Page<StockPledgeList>> list(int page, int size, String column, String order) {

        Qt_item_stock_pledge stockPledge = Qt_item_stock_pledge.t_item_stock_pledge;

        Expression<StockPledgeList> expression = Projections.constructor(
                StockPledgeList.class,
                stockPledge.id,
                stockPledge.financingBody,
                stockPledge.stockNature,
                stockPledge.stockNature,
                stockPledge.holdStockShares,
                stockPledge.auditStatus
        );

        Page<StockPledgeList> pageBean = stockPledgeService.page(page, size, column, order, expression, stockPledge.user.id.eq(getUserId()));

        return Message.success(pageBean);
    }


    @PostMapping("/detail")
    public Message<StockPledge> detail(long stockPledgeId) {
        Qt_item_stock_pledge stockPledge = Qt_item_stock_pledge.t_item_stock_pledge;

        Expression<StockPledge> expression = Projections.constructor(
                StockPledge.class,
                stockPledge.id,
                stockPledge.financingBody,
                stockPledge.stockCode,
                stockPledge.stockNature,
                stockPledge.salesDeadline,
                stockPledge.stockBlock,
                stockPledge.isQuoteOrActualController,
                stockPledge.holdStockShares,
                stockPledge.pledgeStockShares,
                stockPledge.surplusPledgeStockShares,
                stockPledge.loanAmount,
                stockPledge.pledgeRates,
                stockPledge.loanPeriod,
                stockPledge.financingPartyPaysCostRates,
                stockPledge.recentOneYearProfitsDescription,
                stockPledge.purpose,
                stockPledge.repaymentDescription,
                stockPledge.enhancementConfidenceMeasures,
                stockPledge.endTime,
                stockPledge.isUrgent,
                stockPledge.isPlatformAuthentication,
                stockPledge.isFirstHandle,
                stockPledge.auditStatus
        );

        Predicate predicate = stockPledge.user.id.eq(getUserId()).and(stockPledge.id.eq(stockPledgeId));
        StockPledge pledge = stockPledgeService.getOne(expression,predicate);

        return Message.success(pledge);
    }
}

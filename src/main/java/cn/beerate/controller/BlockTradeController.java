package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.BlockTrade;
import cn.beerate.model.dto.BlockTradeList;
import cn.beerate.model.entity.Qt_item_block_trade;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.service.BlockTradeService;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/blocktrade/")
public class BlockTradeController extends UserBaseController {

    private BlockTradeService blockTradeService;

    public BlockTradeController(BlockTradeService blockTradeService) {
        this.blockTradeService = blockTradeService;
    }


    @PostMapping("/add")
    public Message add(t_item_block_trade blockTrade) {
        Message<t_item_block_trade> message = blockTradeService.addBlockTradeByUser(blockTrade, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @GetMapping("/list")
    public Message<Page<BlockTradeList>> list(int page, int size, String column, String order) {
        Qt_item_block_trade blockTrade = Qt_item_block_trade.t_item_block_trade;

        Expression<BlockTradeList> expression = Projections.constructor(
                BlockTradeList.class,
                blockTrade.id,
                blockTrade.blockTradeName,
                blockTrade.exchangeRate,
                blockTrade.underweightShares,
                blockTrade.underweightAmount,
                blockTrade.auditStatus
        );
        Page<BlockTradeList> pageBean = blockTradeService.page(page, size, column, order, expression, blockTrade.user.id.eq(getUserId()));

        return Message.success(pageBean);
    }

    @PostMapping("/detail")
    public Message detail(long blockTradeId) {
        Qt_item_block_trade blockTrade = Qt_item_block_trade.t_item_block_trade;

        Expression<BlockTrade> expression = Projections.constructor(
                BlockTrade.class,
                blockTrade.id,
                blockTrade.blockTradeName,
                blockTrade.stockCode,
                blockTrade.exchangeRate,
                blockTrade.underweightShares,
                blockTrade.underweightAmount,
                blockTrade.underweightIdentification,
                blockTrade.isConfidence,
                blockTrade.expectedReturn,
                blockTrade.confidencePeriod,
                blockTrade.creditIdentification,
                blockTrade.confidenceShare,
                blockTrade.confidenceIsPublic,
                blockTrade.endTime,
                blockTrade.isUrgent,
                blockTrade.isPlatformAuthentication,
                blockTrade.isFirstHandle,
                blockTrade.auditStatus
        );

        Predicate predicate = blockTrade.user.id.eq(getUserId()).and(blockTrade.id.eq(blockTradeId));

        BlockTrade trade = blockTradeService.getOne(expression,predicate);
        if (trade==null){
            return Message.error("项目不存在");
        }

        return Message.success(trade);
    }

    @PostMapping("/update")
    public Message update() {
        return null;
    }

}

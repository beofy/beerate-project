package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.ItemCollectDao;
import cn.beerate.model.InvestPrefer;
import cn.beerate.model.dto.*;
import cn.beerate.model.entity.*;
import cn.beerate.service.ItemCollectService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemCollectServiceImpl extends BaseServiceImpl<t_item_collect> implements ItemCollectService {
    private ItemCollectDao itemCollectDao;

    public ItemCollectServiceImpl(ItemCollectDao itemCollectDao) {
        super(itemCollectDao);
        this.itemCollectDao = itemCollectDao;
    }


    @Transactional
    @Override
    public Message<String> addCollect(String itemType, long userId, long itemId) {
        InvestPrefer prefer = EnumUtils.getEnumIgnoreCase(InvestPrefer.class, itemType);
        if (prefer == null) {
            return Message.error("项目类型错误");
        }

        t_item_collect itemCollect = new t_item_collect();

        t_user user = new t_user();
        user.setId(userId);

        itemCollect.setUser(user);

        switch (prefer) {
            case BLOCK_TRADE:
                t_item_block_trade blockTrade = new t_item_block_trade();
                blockTrade.setId(itemId);
                itemCollect.setBlock_trade(blockTrade);

                break;
            case ITEM_LOAN:
                t_item_loan itemLoan = new t_item_loan();
                itemLoan.setId(itemId);
                itemCollect.setItem_loan(itemLoan);

                break;
            case PRE_IPO:
                t_item_pre_ipo preIpo = new t_item_pre_ipo();
                preIpo.setId(itemId);
                itemCollect.setPre_ipo(preIpo);

                break;
            case STOCK_PLEDGE:
                t_item_stock_pledge stockPledge = new t_item_stock_pledge();
                stockPledge.setId(itemId);
                itemCollect.setStock_pledge(stockPledge);

                break;
            case STOCK_TRANSFER:
                t_item_stock_transfer stockTransfer = new t_item_stock_transfer();
                stockTransfer.setId(itemId);
                itemCollect.setStock_transfer(stockTransfer);

                break;
            default:
                return Message.error("系统异常");
        }

        itemCollectDao.save(itemCollect);

        return Message.ok("收藏成功");
    }

    @Override
    public Message pageOfCollect(int page, int size, String column, String order, long userId, String itemType) {
        InvestPrefer prefer = EnumUtils.getEnumIgnoreCase(InvestPrefer.class, itemType);
        if (prefer == null) {
            return Message.error("项目类型错误");
        }

        Qt_item_collect itemCollect = Qt_item_collect.t_item_collect;
        Predicate predicate = itemCollect.user.id.eq(userId);

        switch (prefer) {
            case BLOCK_TRADE:
                return Message.success(super.page(page, size, column, order, Projections.constructor(
                        BlockTradeList.class,
                        itemCollect.block_trade.id,
                        itemCollect.block_trade.blockTradeName,
                        itemCollect.block_trade.stockCode,
                        itemCollect.block_trade.exchangeRate,
                        itemCollect.block_trade.underweightShares,
                        itemCollect.block_trade.underweightAmount,
                        itemCollect.block_trade.auditStatus
                ), predicate));
            case ITEM_LOAN:
                return Message.success(super.page(page, size, column, order, Projections.constructor(
                        ItemLoanList.class,
                        itemCollect.item_loan.id,
                        itemCollect.item_loan.itemName,
                        itemCollect.item_loan.industryRealm,
                        itemCollect.item_loan.amount,
                        itemCollect.item_loan.period,
                        itemCollect.item_loan.auditStatus
                ), predicate));
            case PRE_IPO:
                return Message.success(super.page(page, size, column, order, Projections.constructor(
                        PreIpoList.class,
                        itemCollect.pre_ipo.id,
                        itemCollect.pre_ipo.preIpoName,
                        itemCollect.pre_ipo.industryRealm,
                        itemCollect.pre_ipo.ratchetTerms,
                        itemCollect.pre_ipo.isNewThirdBoardListing,
                        itemCollect.pre_ipo.iPOBaseDate,
                        itemCollect.pre_ipo.auditStatus
                ), predicate));
            case STOCK_PLEDGE:
                return Message.success(super.page(page, size, column, order, Projections.constructor(
                        StockPledgeList.class,
                        itemCollect.stock_pledge.id,
                        itemCollect.stock_pledge.financingBody,
                        itemCollect.stock_pledge.stockNature,
                        itemCollect.stock_pledge.stockNature,
                        itemCollect.stock_pledge.holdStockShares,
                        itemCollect.stock_pledge.auditStatus
                ), predicate));
            case STOCK_TRANSFER:
                return Message.success(super.page(page, size, column, order, Projections.constructor(
                        StockTransferList.class,
                        itemCollect.stock_transfer.id,
                        itemCollect.stock_transfer.bidName,
                        itemCollect.stock_transfer.isQuoted,
                        itemCollect.stock_transfer.industryRealm,
                        itemCollect.stock_transfer.transferAmount,
                        itemCollect.stock_transfer.auditStatus
                ), predicate));
            default:
                return Message.error("系统异常");
        }
    }

    @Transactional
    @Override
    public Message<String> delCollect(String itemType, long userId, long itemId) {
        t_item_collect itemCollect = findCollect(itemType,userId,itemId);
        if (itemCollect==null){
            return Message.error("该项目未收藏");
        }

        itemCollectDao.deleteById(itemCollect.getId());

        return Message.ok("删除成功");
    }

    @Override
    public t_item_collect findCollect(String itemType, long userId, long itemId) {
        InvestPrefer prefer = EnumUtils.getEnumIgnoreCase(InvestPrefer.class, itemType);
        if (prefer == null) {
            throw new IllegalArgumentException("项目类型错误");
        }

        Qt_item_collect itemCollect = Qt_item_collect.t_item_collect;

        Predicate predicate;
        switch (prefer) {
            case BLOCK_TRADE:
                predicate =  itemCollect.user.id.eq(userId).and(itemCollect.block_trade.id.eq(itemId));
                break;
            case ITEM_LOAN:
                predicate =   itemCollect.user.id.eq(userId).and(itemCollect.item_loan.id.eq(itemId));
                break;
            case PRE_IPO:
                predicate =  itemCollect.user.id.eq(userId).and(itemCollect.pre_ipo.id.eq(itemId));
                break;
            case STOCK_PLEDGE:
                predicate =  itemCollect.user.id.eq(userId).and(itemCollect.stock_pledge.id.eq(itemId));
                break;
            case STOCK_TRANSFER:
                predicate =  itemCollect.user.id.eq(userId).and(itemCollect.stock_transfer.id.eq(itemId));
                break;
            default:
                throw new RuntimeException("项目类型错误");
        }

        return super.getOne(itemCollect,predicate);
    }

}

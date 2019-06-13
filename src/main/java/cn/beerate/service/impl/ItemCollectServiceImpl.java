package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.ItemCollectDao;
import cn.beerate.model.InvestPrefer;
import cn.beerate.model.entity.*;
import cn.beerate.service.ItemCollectService;
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
                t_item_loan loan = new t_item_loan();
                loan.setId(itemId);
                itemCollect.setItem_loan(loan);

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
        return null;
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

        return null;
    }

}

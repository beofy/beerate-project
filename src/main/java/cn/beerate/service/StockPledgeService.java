package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_stock_pledge;

public interface StockPledgeService extends ItemCommonService<t_item_stock_pledge> {

    Message<t_item_stock_pledge> addStockPledge(t_item_stock_pledge stockPledge);

    Message<t_item_stock_pledge> addStockPledgeByUser(t_item_stock_pledge stockPledge, long userId);

    Message<t_item_stock_pledge> addStockPledgeByAdmin(t_item_stock_pledge stockPledge, long adminId);
}

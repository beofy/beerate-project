package cn.beerate.service.impl;

import cn.beerate.dao.StockPledgeDao;
import cn.beerate.model.entity.t_item_stock_pledge;
import cn.beerate.service.StockPledgeService;

public class StockPledgeServiceImpl extends BaseServiceImpl<t_item_stock_pledge> implements StockPledgeService {

    private StockPledgeDao stockPledgeDao;

    public StockPledgeServiceImpl(StockPledgeDao stockPledgeDao) {
        super(stockPledgeDao);
        this.stockPledgeDao = stockPledgeDao;
    }
}

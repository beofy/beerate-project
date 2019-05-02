package cn.beerate.service.impl;

import cn.beerate.dao.StockTransferDao;
import cn.beerate.model.entity.t_item_stock_transfer;
import cn.beerate.service.StockPledgeService;

public class StockTransferServiceImpl extends BaseServiceImpl<t_item_stock_transfer> implements StockPledgeService {

    private StockTransferDao stockTransferDao;

    public StockTransferServiceImpl(StockTransferDao stockTransferDao) {
        super(stockTransferDao);
        this.stockTransferDao = stockTransferDao;
    }
}

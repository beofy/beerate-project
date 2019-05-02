package cn.beerate.service.impl;

import cn.beerate.dao.StockTransferDao;
import cn.beerate.model.entity.t_item_stock_transfer;
import cn.beerate.service.StockTransferSerivce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StockTransferServiceImpl extends BaseServiceImpl<t_item_stock_transfer> implements StockTransferSerivce {

    private StockTransferDao stockTransferDao;

    public StockTransferServiceImpl(StockTransferDao stockTransferDao) {
        super(stockTransferDao);
        this.stockTransferDao = stockTransferDao;
    }
}

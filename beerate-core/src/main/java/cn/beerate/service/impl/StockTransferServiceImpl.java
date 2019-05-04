package cn.beerate.service.impl;

import cn.beerate.common.Message;
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

    public Message<t_item_stock_transfer> addStockTransfer(t_item_stock_transfer stockTransfer){
        // TODO: 2019/5/4   设置初始化字段参数

        return Message.success(stockTransferDao.save(stockTransfer));
    }

    public Message<t_item_stock_transfer> addStockTransferByUser(t_item_stock_transfer stockTransfer,long userId){
        stockTransfer.getUser().setId(userId);

        return addStockTransfer(stockTransfer);
    }

    public Message<t_item_stock_transfer> addStockTransferByAdmin(t_item_stock_transfer stockTransfer,long adminId){
        stockTransfer.getAdmin().setId(adminId);

        return addStockTransfer(stockTransfer);
    }

}

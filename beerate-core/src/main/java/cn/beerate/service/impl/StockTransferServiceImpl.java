package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.StockTransferDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ModelValidate;
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

        Message<String> message = ModelValidate.stockTransferValid(stockTransfer);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.success(stockTransferDao.save(stockTransfer));
    }

    public Message<t_item_stock_transfer> addStockTransferByUser(t_item_stock_transfer stockTransfer,long userId){
        stockTransfer.getUser().setId(userId);
        stockTransfer.setAuditStatus(AuditStatus.WAIT_AUDIT);

        return addStockTransfer(stockTransfer);
    }

    public Message<t_item_stock_transfer> addStockTransferByAdmin(t_item_stock_transfer stockTransfer,long adminId){
        stockTransfer.getAdmin().setId(adminId);
        stockTransfer.setAuditStatus(AuditStatus.PASS_AUDIT);

        return addStockTransfer(stockTransfer);
    }

}

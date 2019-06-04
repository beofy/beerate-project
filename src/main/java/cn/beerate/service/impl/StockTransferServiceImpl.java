package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.StockTransferDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ModelValidate;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_item_stock_transfer;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.StockTransferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StockTransferServiceImpl extends BaseServiceImpl<t_item_stock_transfer> implements StockTransferService {

    private StockTransferDao stockTransferDao;

    public StockTransferServiceImpl(StockTransferDao stockTransferDao) {
        super(stockTransferDao);
        this.stockTransferDao = stockTransferDao;
    }

    @Transactional
    public Message<t_item_stock_transfer> addStockTransfer(t_item_stock_transfer stockTransfer){

        Message<String> message = ModelValidate.stockTransferValid(stockTransfer);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.success(stockTransferDao.save(stockTransfer));
    }

    @Transactional
    public Message<t_item_stock_transfer> addStockTransferByUser(t_item_stock_transfer stockTransfer,long userId){
        t_user user = new t_user();
        user.setId(userId);

        stockTransfer.setUser(user);
        stockTransfer.setAuditStatus(AuditStatus.WAIT_AUDIT);

        return addStockTransfer(stockTransfer);
    }

    @Transactional
    public Message<t_item_stock_transfer> addStockTransferByAdmin(t_item_stock_transfer stockTransfer,long adminId){
        t_admin admin = new t_admin();
        admin.setId(adminId);

        stockTransfer.setAdmin(admin);
        stockTransfer.setAuditStatus(AuditStatus.PASS_AUDIT);

        return addStockTransfer(stockTransfer);
    }

}

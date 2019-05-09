package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.StockPledgeDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ModelValidate;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_item_stock_pledge;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.StockPledgeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StockPledgeServiceImpl extends BaseServiceImpl<t_item_stock_pledge> implements StockPledgeService {

    private StockPledgeDao stockPledgeDao;

    public StockPledgeServiceImpl(StockPledgeDao stockPledgeDao) {
        super(stockPledgeDao);
        this.stockPledgeDao = stockPledgeDao;
    }

    @Transactional
    public Message<t_item_stock_pledge> addStockPledge(t_item_stock_pledge stockPledge) {

        Message<String> message = ModelValidate.stockPledgeValid(stockPledge);
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.success(stockPledgeDao.save(stockPledge));
    }

    @Transactional
    public Message<t_item_stock_pledge> addStockPledgeByUser(t_item_stock_pledge stockPledge, long userId) {
        t_user user = new t_user();
        user.setId(userId);

        stockPledge.setUser(user);
        stockPledge.setAuditStatus(AuditStatus.WAIT_AUDIT);

        return addStockPledge(stockPledge);
    }

    @Transactional
    public Message<t_item_stock_pledge> addStockPledgeByAdmin(t_item_stock_pledge stockPledge, long adminId) {
        t_admin admin = new t_admin();
        admin.setId(adminId);

        stockPledge.setAdmin(admin);
        stockPledge.setAuditStatus(AuditStatus.PASS_AUDIT);

        return addStockPledge(stockPledge);
    }

}

package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.StockPledgeDao;
import cn.beerate.model.entity.t_item_stock_pledge;
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

    public Message<t_item_stock_pledge> addStockPledge(t_item_stock_pledge stockPledge){
        // TODO: 2019/5/4   设置初始化字段参数
        return Message.success(stockPledgeDao.save(stockPledge));
    }

    public Message<t_item_stock_pledge> addStockPledgeByUser(t_item_stock_pledge stockPledge,long userId){
        stockPledge.getUser().setId(userId);

        return addStockPledge(stockPledge);
}

    public Message<t_item_stock_pledge> addStockPledgeByAdmin(t_item_stock_pledge stockPledge,long adminId){
        stockPledge.getAdmin().setId(adminId);

        return addStockPledge(stockPledge);
    }

}

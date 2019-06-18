package cn.beerate.dao;

import cn.beerate.dao.impl.StockPledgeRepository;
import cn.beerate.model.entity.t_item_stock_pledge;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPledgeDao extends ItemCommonDao<t_item_stock_pledge>, StockPledgeRepository {
}

package cn.beerate.dao;

import cn.beerate.dao.impl.StockPledgeRepository;
import cn.beerate.dao.impl.StockTransferRepository;
import cn.beerate.model.entity.t_item_stock_transfer;
import cn.beerate.service.StockTransferService;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTransferDao extends ItemCommonDao<t_item_stock_transfer> , StockTransferRepository {
}

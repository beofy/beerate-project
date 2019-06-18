package cn.beerate.dao;

import cn.beerate.dao.impl.BlockTradeRepository;
import cn.beerate.model.entity.t_item_block_trade;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockTradeDao extends ItemCommonDao<t_item_block_trade>, BlockTradeRepository {
}

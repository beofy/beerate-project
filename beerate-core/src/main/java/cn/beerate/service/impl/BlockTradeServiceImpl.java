package cn.beerate.service.impl;

import cn.beerate.dao.BlockTradeDao;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.service.BlockTradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BlockTradeServiceImpl extends BaseServiceImpl<t_item_block_trade> implements BlockTradeService {
    private BlockTradeDao blockTradeDao;

    public BlockTradeServiceImpl(BlockTradeDao blockTradeDao) {
        super(blockTradeDao);
        this.blockTradeDao = blockTradeDao;
    }
}

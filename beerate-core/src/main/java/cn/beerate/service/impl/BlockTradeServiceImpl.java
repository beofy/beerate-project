package cn.beerate.service.impl;

import cn.beerate.common.Message;
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

    /**
     * 添加大宗交易
     */
    public Message<t_item_block_trade> addBlockTrade(t_item_block_trade blockTrade){
        // TODO: 2019/5/4   设置初始化字段参数

       return Message.success(blockTradeDao.save(blockTrade));

    }

    /**
     *  添加大宗交易-前台用户
     */
    public Message<t_item_block_trade> addBlockTradeByUser(t_item_block_trade blockTrade,long userId){
        blockTrade.getUser().setId(userId);

        return addBlockTrade(blockTrade);
    }

    /**
     *  添加大宗交易-后台管理员
     */
    public Message<t_item_block_trade> addBlockTradeByAdmin(t_item_block_trade blockTrade,long adminId){
        blockTrade.getAdmin().setId(adminId);

        return addBlockTrade(blockTrade);
    }

}

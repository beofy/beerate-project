package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_block_trade;

public interface BlockTradeService extends ItemCommonService<t_item_block_trade> {

    /**
     * 添加大宗交易
     */
    Message<t_item_block_trade> addBlockTrade(t_item_block_trade blockTrade);

    /**
     * 添加大宗交易-前台用户
     */
    Message<t_item_block_trade> addBlockTradeByUser(t_item_block_trade blockTrade, long userId);

    /**
     * 添加大宗交易-后台管理员
     */
    Message<t_item_block_trade> addBlockTradeByAdmin(t_item_block_trade blockTrade, long adminId);
}

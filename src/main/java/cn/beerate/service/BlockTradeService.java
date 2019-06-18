package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.BlockTrade;
import cn.beerate.model.dto.BlockTradeDetail;
import cn.beerate.model.dto.MyBlockTrade;
import cn.beerate.model.entity.t_item_block_trade;
import org.springframework.data.domain.Page;

public interface BlockTradeService extends ItemCommonService<t_item_block_trade> {

    /**
     * 查询我的融资列表
     */
    Page<MyBlockTrade> pageMyBlockTradeUser(int page, int size, String column, String order, long userId);

    /**
     * 查询项目详情
     */
    BlockTradeDetail BlockTradeDetailByUser(long blockTradeId, long userId);

    /**
     * 更新融资项目信息
     */
    Message<t_item_block_trade> updateItemByUser(t_item_block_trade blockTrade, long itemId, long userId) ;

    /**
     * 项目集列表
     */
    Page<BlockTrade> pageBlockTrade(int page, int size, String column, String order);

}

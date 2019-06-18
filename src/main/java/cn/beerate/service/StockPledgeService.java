package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.*;
import cn.beerate.model.entity.t_item_stock_pledge;
import org.springframework.data.domain.Page;

public interface StockPledgeService extends ItemCommonService<t_item_stock_pledge> {

    /**
     * 查询我的融资列表
     */
    Page<MyStockPledge> pageMyStockPledge(int page, int size, String column, String order, long userId);

    /**
     * 查询项目详情
     */
    StockPledgeDetail stockPledgeDetailByUser(long stockPledgeId, long userId);

    /**
     * 更新融资项目信息
     */
    Message<t_item_stock_pledge> updateItemByUser(t_item_stock_pledge stockPledge, long itemId, long userId) ;

    /**
     * 项目集列表
     */
    Page<StockPledge> pageStockPledge(int page, int size, String column, String order);

}

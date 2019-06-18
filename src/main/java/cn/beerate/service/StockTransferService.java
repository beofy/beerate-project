package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.*;
import cn.beerate.model.entity.t_item_stock_pledge;
import cn.beerate.model.entity.t_item_stock_transfer;
import org.springframework.data.domain.Page;

public interface StockTransferService extends ItemCommonService<t_item_stock_transfer>{

    /**
     * 查询我的融资列表
     */
    Page<MyStockTransfer> pageMyStockTransfer(int page, int size, String column, String order, long userId);

    /**
     * 查询项目详情
     */
    StockTransferDetail stockTransferDetailByUser(long stockTransferId, long userId);

    /**
     * 更新融资项目信息
     */
    Message<t_item_stock_transfer> updateItemByUser(t_item_stock_transfer stockTransfer, long itemId, long userId) ;

    /**
     * 项目集列表
     */
    Page<StockTransfer> pageStockTransfer(int page, int size, String column, String order);

}

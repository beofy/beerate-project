package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_stock_transfer;

public interface StockTransferService extends IBaseService<t_item_stock_transfer>{

    Message<t_item_stock_transfer> addStockTransfer(t_item_stock_transfer stockTransfer);

    Message<t_item_stock_transfer> addStockTransferByUser(t_item_stock_transfer stockTransfer, long userId);

    Message<t_item_stock_transfer> addStockTransferByAdmin(t_item_stock_transfer stockTransfer, long adminId);

}

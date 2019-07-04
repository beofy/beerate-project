package cn.beerate.dao.impl;

import cn.beerate.model.dto.MyStockTransfer;
import cn.beerate.model.dto.StockTransfer;
import cn.beerate.model.dto.StockTransferDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockTransferRepository {

    /**
     * 我的项目列表
     */
    Page<MyStockTransfer> pageMyStockTransferByUser(Pageable pageable, long userId);

    /**
     * 查询项目详情
     */
    StockTransferDetail  stockTransferDetailByUser(long stockTransferId, long userId);

    /**
     * 项目集列表
     */
    Page<StockTransfer> pageStockTransfer(Pageable pageable);

}

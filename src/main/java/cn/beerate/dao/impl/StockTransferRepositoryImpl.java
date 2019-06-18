package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.MyBlockTrade;
import cn.beerate.model.dto.MyStockTransfer;
import cn.beerate.model.dto.StockTransfer;
import cn.beerate.model.dto.StockTransferDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class StockTransferRepositoryImpl implements StockTransferRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<MyStockTransfer> pageMyStockTransferByUser(Pageable pageable, long userId) {
        return null;
    }

    @Override
    public StockTransferDetail stockTransferDetailByUser(long stockTransferId, long userId) {
        return null;
    }

    @Override
    public Page<StockTransfer> pageStockTransfer(Pageable pageable) {
        return null;
    }
}

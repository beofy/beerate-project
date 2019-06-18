package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.MyBlockTrade;
import cn.beerate.model.dto.MyStockPledge;
import cn.beerate.model.dto.StockPledge;
import cn.beerate.model.dto.StockPledgeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class StockPledgeRepositoryImpl implements StockPledgeRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<MyStockPledge> pageMyStockPledgeByUser(Pageable pageable, long userId) {
        return null;
    }

    @Override
    public StockPledgeDetail stockPledgeDetailByUser(long stockPledgeId, long userId) {
        return null;
    }

    @Override
    public Page<StockPledge> pageStockPledge(Pageable pageable) {
        return null;
    }
}

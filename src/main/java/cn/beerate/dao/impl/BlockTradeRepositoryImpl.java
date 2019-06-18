package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.BlockTrade;
import cn.beerate.model.dto.BlockTradeDetail;
import cn.beerate.model.dto.MyBlockTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class BlockTradeRepositoryImpl implements BlockTradeRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<MyBlockTrade> pageMyBlockTradeByUser(Pageable pageable, long userId) {
        return null;
    }

    @Override
    public BlockTradeDetail blockTradeDetailByUser(long loanId, long userId) {
        return null;
    }

    @Override
    public Page<BlockTrade> pageBlockTrade(Pageable pageable) {
        return null;
    }
}

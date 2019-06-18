package cn.beerate.dao.impl;

import cn.beerate.model.dto.BlockTrade;
import cn.beerate.model.dto.BlockTradeDetail;
import cn.beerate.model.dto.Loan;
import cn.beerate.model.dto.MyBlockTrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlockTradeRepository {

    /**
     * 我的项目列表
     */
    Page<MyBlockTrade> pageMyBlockTradeByUser(Pageable pageable, long userId);

    /**
     * 查询项目详情
     */
    BlockTradeDetail blockTradeDetailByUser(long loanId, long userId);

    /**
     * 项目集列表
     */
    Page<BlockTrade> pageBlockTrade(Pageable pageable);
}

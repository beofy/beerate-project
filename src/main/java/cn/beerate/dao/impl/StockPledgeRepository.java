package cn.beerate.dao.impl;

import cn.beerate.model.dto.MyStockPledge;
import cn.beerate.model.dto.StockPledge;
import cn.beerate.model.dto.StockPledgeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockPledgeRepository {

    /**
     * 我的项目列表
     */
    Page<MyStockPledge> pageMyStockPledgeByUser(Pageable pageable, long userId);

    /**
     * 查询项目详情
     */
    StockPledgeDetail stockPledgeDetailByUser(long stockPledgeId, long userId);

    /**
     * 项目集列表
     */
    Page<StockPledge> pageStockPledge(Pageable pageable);
}

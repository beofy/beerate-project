package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

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
        String querySql="SELECT id, financingBody, stockCode, stockNature, salesDeadline, stockBlock, isQuoteOrActualController, holdStockShares, pledgeStockShares, surplusPledgeStockShares, loanAmount, pledgeRates, loanPeriod, financingPartyPaysCostRates, recentOneYearProfitsDescription, purpose, repaymentDescription, enhancementConfidenceMeasures, endTime, isUrgent, isPlatformAuthentication, isFirstHandle, auditStatus FROM t_item_stock_pledge where userId=:userId";

        Map<String,Object> args= new HashMap<>();
        args.put("id",stockPledgeId);
        args.put("userId",userId);

        return genericRepository.getObject(querySql,args, StockPledgeDetail.class);
    }

    @Override
    public Page<StockPledge> pageStockPledge(Pageable pageable) {
        return null;
    }
}

package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.MyStockPledge;
import cn.beerate.model.dto.StockPledge;
import cn.beerate.model.dto.StockPledgeDetail;
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
        String querySql="SELECT id, financingBody, stockCode, stockNature, stockBlock, holdStockShares, auditStatus FROM t_item_stock_pledge WHERE userId = :userId";
        String countSql="SELECT COUNT(1) FROM t_item_stock_pledge WHERE userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("userId",userId);
        return genericRepository.getPage(querySql,countSql,args,pageable, MyStockPledge.class);
    }

    @Override
    public StockPledgeDetail stockPledgeDetailByUser(long stockPledgeId, long userId) {
        String querySql="SELECT id, financingBody, stockCode, stockNature, salesDeadline, stockBlock, isQuoteOrActualController, holdStockShares, pledgeStockShares, surplusPledgeStockShares, loanAmount, pledgeRates, loanPeriod, financingPartyPaysCostRates, recentOneYearProfitsDescription, purpose, repaymentDescription, enhancementConfidenceMeasures, endTime, isUrgent, isPlatformAuthentication, isFirstHandle, auditStatus, description, userId, adminId FROM t_item_stock_pledge WHERE id = :id AND userId =:userId";

        Map<String,Object> args= new HashMap<>();
        args.put("id",stockPledgeId);
        args.put("userId",userId);

        return genericRepository.getObject(querySql,args, StockPledgeDetail.class);
    }

    @Override
    public Page<StockPledge> pageStockPledge(Pageable pageable) {
        String querySql="SELECT id, financingBody, stockCode, stockNature, stockBlock, loanAmount, pledgeRates, loanPeriod FROM t_item_stock_pledge WHERE auditStatus = 'PASS_AUDIT'";
        String countSql="SELECT count(1) FROM t_item_stock_pledge WHERE auditStatus = 'PASS_AUDIT'";

        return genericRepository.getPage(querySql,countSql,null,pageable,StockPledge.class);
    }
}

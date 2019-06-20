package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.MyStockTransfer;
import cn.beerate.model.dto.StockTransfer;
import cn.beerate.model.dto.StockTransferDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StockTransferRepositoryImpl implements StockTransferRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<MyStockTransfer> pageMyStockTransferByUser(Pageable pageable, long userId) {
        String querySql="SELECT id, bidName, isQuoted, industryRealm, transferAmount, auditStatus FROM t_item_stock_transfer WHERE userId = :userId";
        String countSql="SELECT COUNT(1) FROM t_item_stock_transfer WHERE userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("userId",userId);
        return genericRepository.getPage(querySql,countSql,args,pageable, MyStockTransfer.class);
    }

    @Override
    public StockTransferDetail stockTransferDetailByUser(long stockTransferId, long userId) {
        String querySql = "SELECT id, bidName, isQuoted, companyNameIsPublic, companyName, industryRealm, currency, lastYearProfits, currentValuation, transferAmount, isPrivacyEquityRatio, equityRatio, investLightSpot, contact, contactMobile, contentDescription, endTime, isUrgent, isPlatformAuthentication, isFirstHandle, auditStatus,description,userId,adminId FROM t_item_stock_transfer WHERE id = :id AND userId = :userId";

        Map<String, Object> args = new HashMap<>();
        args.put("id", stockTransferId);
        args.put("userId", userId);

        return genericRepository.getObject(querySql, args, StockTransferDetail.class);
    }

    @Override
    public Page<StockTransfer> pageStockTransfer(Pageable pageable) {
        String querySql="SELECT id, bidName, companyNameIsPublic, companyName, contact, isQuoted, currentValuation, transferAmount, isPrivacyEquityRatio, equityRatio FROM t_item_stock_transfer WHERE auditStatus = 'PASS_AUDIT'";
        String countSql="SELECT count(1) FROM t_item_stock_transfer WHERE auditStatus = 'PASS_AUDIT'";

        return genericRepository.getPage(querySql,countSql,null,pageable,StockTransfer.class);
    }
}

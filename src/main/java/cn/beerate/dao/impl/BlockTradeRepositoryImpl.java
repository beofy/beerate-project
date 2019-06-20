package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.BlockTrade;
import cn.beerate.model.dto.BlockTradeDetail;
import cn.beerate.model.dto.MyBlockTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BlockTradeRepositoryImpl implements BlockTradeRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<MyBlockTrade> pageMyBlockTradeByUser(Pageable pageable, long userId) {
        String querySql="SELECT id, blockTradeName ,stockCode ,exchangeRate, underweightShares, underweightAmount ,auditStatus FROM t_item_block_trade WHERE userId = :userId";
        String countSql="SELECT COUNT(1) FROM t_item_block_trade WHERE userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("userId",userId);
        return genericRepository.getPage(querySql,countSql,args,pageable, MyBlockTrade.class);
    }

    @Override
    public BlockTradeDetail blockTradeDetailByUser(long blockTradeId, long userId) {
        String querySql="SELECT id, blockTradeName, stockCode, exchangeRate, underweightShares , underweightAmount, underweightIdentification, isConfidence, expectedReturn, confidencePeriod , creditIdentification, confidenceShare, confidenceIsPublic, endTime, isUrgent , isPlatformAuthentication, isFirstHandle, auditStatus, description, adminId , userId FROM t_item_block_trade WHERE id = :id AND userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("id",blockTradeId);
        args.put("userId",userId);

        return genericRepository.getObject(querySql,args,BlockTradeDetail.class);
    }

    @Override
    public Page<BlockTrade> pageBlockTrade(Pageable pageable) {
        String querySql = "SELECT id, blockTradeName, stockCode, exchangeRate, underweightShares, underweightAmount, isConfidence, underweightIdentification FROM t_item_block_trade WHERE auditStatus = 'PASS_AUDIT'";
        String countSql = "SELECT count(1) FROM t_item_block_trade WHERE auditStatus = 'PASS_AUDIT'";

        return genericRepository.getPage(querySql, countSql, null, pageable, BlockTrade.class);
    }
}

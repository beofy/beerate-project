package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.UserItemCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemCollectRepositoryImpl implements ItemCollectRepository {

    @Autowired
    private GenericRepository genericRepository;

    public Page<UserItemCollect> userItemCollect(Pageable pageable, long userId){
        String querySql="SELECT id, ( CASE collect.itemType WHEN 'STOCK_TRANSFER' THEN (SELECT transfer.bidName FROM t_item_stock_transfer transfer WHERE transfer.id = collect.itemId ) WHEN 'PRE_IPO' THEN (SELECT preipo.preIpoName FROM t_item_pre_ipo preipo WHERE preipo.id = collect.itemId ) WHEN 'BLOCK_TRADE' THEN (SELECT trade.blockTradeName FROM t_item_block_trade trade WHERE trade.id = collect.itemId ) WHEN 'STOCK_PLEDGE' THEN (SELECT pledge.financingBody FROM t_item_stock_pledge pledge WHERE pledge.id = collect.itemId ) WHEN 'ITEM_LOAN' THEN (SELECT loan.itemName FROM t_item_loan loan WHERE loan.id = collect.itemId ) END ) AS `name`, itemType FROM t_item_collect collect WHERE collect.userId = :userId";
        String countSql="SELECT COUNT(1) FROM t_item_collect collect WHERE collect.userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("userId",userId);

        return genericRepository.getPage(querySql,countSql,args,pageable,UserItemCollect.class);
    }
}

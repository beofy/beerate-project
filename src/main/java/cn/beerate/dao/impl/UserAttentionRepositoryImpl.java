package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.Projector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserAttentionRepositoryImpl implements UserAttentionRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<Projector> attention(Pageable pageable, long userId) {
        String querySql="SELECT business.userId, business.`name`, business.`company`, business.`title`, ( SELECT COUNT(1) FROM t_user_visitor visitor WHERE visitor.visitorUserId = business.userId ) AS `visitorTotals`, ( SELECT COUNT(1) FROM t_user_item_accept accept WHERE accept.acceptUserId = business.userId ) AS `receiveItemTotals` FROM t_user_attention attention LEFT JOIN t_user_business business ON business.userId = attention.attentionUserId WHERE attention.userId = :userId";
        String countSql="SELECT count(1) FROM t_user_attention attention LEFT JOIN t_user_business business ON business.userId = attention.attentionUserId WHERE attention.userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("userId",userId);

        return genericRepository.getPage(querySql,countSql,args,pageable, Projector.class);
    }

    @Override
    public Page<Projector> beAttention(Pageable pageable, long attentionUserId) {
        String querySql="SELECT business.userId, business.`name`, business.`company`, business.`title`, ( SELECT COUNT(1) FROM t_user_visitor visitor WHERE visitor.visitorUserId = business.userId ) AS `visitorTotals`, ( SELECT COUNT(1) FROM t_user_item_accept accept WHERE accept.acceptUserId = business.userId ) AS `receiveItemTotals` FROM t_user_attention attention LEFT JOIN t_user_business business ON business.userId = attention.userId WHERE attention.attentionUserId = :attentionUserId";
        String countSql="SELECT count(1) FROM t_user_attention attention LEFT JOIN t_user_business business ON business.userId = attention.userId WHERE attention.attentionUserId = :attentionUserId";

        Map<String,Object> args= new HashMap<>();
        args.put("attentionUserId",attentionUserId);

        return genericRepository.getPage(querySql,countSql,args,pageable, Projector.class);
    }
}

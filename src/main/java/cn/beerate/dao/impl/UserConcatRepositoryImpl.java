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
public class UserConcatRepositoryImpl implements UserConcatRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<Projector> contact(Pageable pageable, long userId) {
        String querySql="SELECT business.userId, business.`name`, business.`company`, business.`title`, ( SELECT COUNT(1) FROM t_user_visitor visitor WHERE visitor.visitorUserId = business.userId ) AS `visitorTotals`, ( SELECT COUNT(1) FROM t_user_item_accept accept WHERE accept.acceptUserId = business.userId ) AS `receiveItemTotals` FROM t_user_contact contact LEFT JOIN t_user_business business ON business.userId = contact.contactUserId WHERE contact.userId = :userId";
        String countSql="SELECT count(1) FROM t_user_contact contact LEFT JOIN t_user_business business ON business.userId = contact.contactUserId WHERE contact.userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("userId",userId);

        return genericRepository.getPage(querySql,countSql,args,pageable, Projector.class);
    }

    @Override
    public Page<Projector> beContact(Pageable pageable, long contactUserId) {
        String querySql="SELECT business.userId, business.`name`, business.`company`, business.`title`, ( SELECT COUNT(1) FROM t_user_visitor visitor WHERE visitor.visitorUserId = business.userId ) AS `visitorTotals`, ( SELECT COUNT(1) FROM t_user_item_accept accept WHERE accept.acceptUserId = business.userId ) AS `receiveItemTotals` FROM t_user_contact contact LEFT JOIN t_user_business business ON business.userId = contact.userId WHERE contact.contactUserId = :contactUserId";
        String countSql="SELECT count(1) FROM t_user_contact contact LEFT JOIN t_user_business business ON business.userId = contact.userId WHERE contact.contactUserId = :contactUserId";

        Map<String,Object> args= new HashMap<>();
        args.put("contactUserId",contactUserId);

        return genericRepository.getPage(querySql,countSql,args,pageable, Projector.class);
    }
}

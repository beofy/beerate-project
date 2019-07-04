package cn.beerate.dao.impl;


import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.Projector;
import cn.beerate.model.dto.ProjectorDetail;
import cn.beerate.model.dto.ProjectorIntro;
import cn.beerate.model.dto.UserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserBusinessRepositoryImpl implements UserBusinessRepository {

    @Autowired
    private GenericRepository genericRepository;

    public UserBusiness findUserBusinessDetailByUser(long userId) {
        String sql = "SELECT id, createTime, updateTime,aboutText , address, auditStatus, businessCardUri, company, department , email, investPrefer, NAME AS `name`, title, telWork , telCell, verifyTime, workText FROM t_user_business WHERE userId = :userId";
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);

        return genericRepository.getObject(sql,map,UserBusiness.class);
    }

    @Override
    public Page<Projector> pageOfProjector(Pageable pageable) {
        String querySql="SELECT `userId`, `name`, `company`, `title`, ( SELECT count(1) FROM t_user_visitor visitor WHERE visitor.visitorUserId = business.userId ) AS `visitorTotals`, ( SELECT count(1) FROM t_user_item_accept accept WHERE accept.acceptUserId = business.userId ) AS `receiveItemTotals` FROM t_user_business business WHERE auditStatus = 'PASS_AUDIT'";
        String countSql="SELECT COUNT(1) FROM t_user_business WHERE auditStatus = 'PASS_AUDIT'";


        return genericRepository.getPage(querySql,countSql,null,pageable, Projector.class);
    }

    @Override
    public ProjectorIntro projectorIntro(long userId) {
        String querySql="SELECT id, `name`, company, title, ( SELECT COUNT(1) FROM t_user_attention attention WHERE attention.userId = business.userId ) AS `attentionTotals`, ( SELECT COUNT(1) FROM t_user_attention attention WHERE attention.attentionUserId = business.userId ) AS `beAttentionTotals`, ( SELECT COUNT(1) FROM t_user_contact contact WHERE contact.userId = business.userId ) AS `contactTotals`, ( SELECT COUNT(1) FROM t_user_contact contact WHERE contact.contactUserId = business.userId ) AS `beContactTotals`, ( SELECT COUNT(1) FROM t_user_visitor visitor WHERE visitor.visitorUserId = business.userId ) AS `visitorTotals`, ( SELECT COUNT(1) FROM t_user_item_accept accept WHERE accept.acceptUserId = business.userId ) AS `acceptItemTotals` FROM t_user_business business WHERE business.userId = :userId";

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);

        return genericRepository.getObject(querySql,map,ProjectorIntro.class);
    }

    @Override
    public ProjectorDetail projectorDetail(long userId) {
        String querySql="SELECT id, `name`, company, department, title, telCell, telWork, address, email, investPrefer, aboutText, workText, ( SELECT COUNT(1) FROM t_user_attention attention WHERE attention.userId = business.userId ) AS `attentionTotals`, ( SELECT COUNT(1) FROM t_user_attention attention WHERE attention.attentionUserId = business.userId ) AS `beAttentionTotals`, ( SELECT COUNT(1) FROM t_user_contact contact WHERE contact.userId = business.userId ) AS `contactTotals`, ( SELECT COUNT(1) FROM t_user_contact contact WHERE contact.contactUserId = business.userId ) AS `beContactTotals`, ( SELECT COUNT(1) FROM t_user_visitor visitor WHERE visitor.visitorUserId = business.userId ) AS `visitorTotals`, ( SELECT COUNT(1) FROM t_user_item_accept accept WHERE accept.acceptUserId = business.userId ) AS `acceptItemTotals` FROM t_user_business business WHERE business.userId = :userId";

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);

        return genericRepository.getObject(querySql,map,ProjectorDetail.class);
    }
}

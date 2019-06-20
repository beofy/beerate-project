package cn.beerate.dao.impl;


import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.MyStockPledge;
import cn.beerate.model.dto.Projector;
import cn.beerate.model.dto.ProjectorDetail;
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
        String querySql="SELECT `userId`, `name`, `company`, `title`, (SELECT count(1) FROM t_user_visitor visitor WHERE visitor.visitorUserId=userId) as `visitorTotals`, (SELECT count(1) FROM t_user_item_accept accept WHERE accept.userId=userId) as `receiveItemTotals` FROM t_user_business WHERE auditStatus = 'PASS_AUDIT'";
        String countSql="SELECT COUNT(1) FROM t_user_business WHERE auditStatus = 'PASS_AUDIT'";

        return genericRepository.getPage(querySql,countSql,null,pageable, Projector.class);
    }

    @Override
    public Projector projectorIntro() {
        return null;
    }

    @Override
    public ProjectorDetail projectorDetail() {
        return null;
    }
}

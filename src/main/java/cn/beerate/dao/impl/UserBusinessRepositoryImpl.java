package cn.beerate.dao.impl;


import cn.beerate.dao.support.GenericRepository;
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
        String sql = "SELECT id, createTime, updateTime, user_id, aboutText , address, auditStatus, businessCardUri, company, department , email, investPrefer, NAME AS `name`, title, telWork , telCell, verifyTime, workText FROM t_user_business WHERE user_id = :user_id";
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",userId);

        return genericRepository.getObject(sql,map,UserBusiness.class);
    }

    @Override
    public Page<Projector> pageOfProjector(Pageable pageable) {
        return null;
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

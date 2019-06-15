package cn.beerate.dao.impl;


import cn.beerate.dao.support.IGenericDao;
import cn.beerate.model.dto.UserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

    @Autowired
    private IGenericDao iGenericDao;

    public UserBusiness findUserBusinessDetailByUser(long userId) {
        String sql = "SELECT id, createTime, updateTime, user_id, aboutText , address, auditStatus, businessCardUri, company, department , email, investPrefer, NAME AS `name`, title, telWork , telCell, verifyTime, workText FROM t_user_business WHERE user_id = :user_id";
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",userId);

        return iGenericDao.getObject(sql,map,UserBusiness.class);
    }

}

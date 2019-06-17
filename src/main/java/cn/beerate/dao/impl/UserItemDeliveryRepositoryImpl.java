package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.UserItemDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserItemDeliveryRepositoryImpl implements UserItemDeliveryRepository {

    @Autowired
    private GenericRepository genericRepository;

    public  Page<UserItemDelivery> userItemDelivery(Pageable pageable,long userId,long acceptUserId){
        String querySql="";
        String countSql="";

        Map<String,Object> args= new HashMap<>();
        args.put("user_id",userId);
        args.put("accept_user_id",acceptUserId);

       return genericRepository.getPage(querySql,countSql,args,pageable, UserItemDelivery.class);
    }
}

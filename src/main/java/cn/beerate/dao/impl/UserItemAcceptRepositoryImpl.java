package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.UserItemAccept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserItemAcceptRepositoryImpl implements UserItemAcceptRepository {

    @Autowired
    private GenericRepository genericRepository;

    public Page<UserItemAccept> userItemAccept(Pageable pageable,long userId){
        String querySql="";
        String countSql="";

        Map<String,Object> args= new HashMap<>();
        args.put("user_id",userId);

        return genericRepository.getPage(querySql,countSql,args,pageable,UserItemAccept.class);
    }
}

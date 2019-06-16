package cn.beerate.dao;

import cn.beerate.dao.impl.UserBusinessRepository;
import cn.beerate.model.entity.t_user_business;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBusinessDao extends IBaseDao<t_user_business>, UserBusinessRepository {

    t_user_business findByUserId(long userId);

}

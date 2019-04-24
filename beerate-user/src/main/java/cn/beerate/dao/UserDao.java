package cn.beerate.dao;

import cn.beerate.model.entity.t_user;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends UserAndAdminDao<t_user> {

}

package cn.beerate.dao;

import cn.beerate.model.entity.t_admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends UserAndAdminDao<t_admin> {

}

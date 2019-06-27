package cn.beerate.dao;

import cn.beerate.model.entity.t_admin_right;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRightDao extends IBaseDao<t_admin_right> {

    /**
     * 根据adminId查询权限
     */
    List<t_admin_right> findByAdminId(long adminId);

    /**
     * 根据adminId删除
     */
    void deleteByAdminId(long adminId);
}

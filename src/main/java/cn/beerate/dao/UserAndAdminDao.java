package cn.beerate.dao;

import cn.beerate.model.UserAndAdminModel;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserAndAdminDao<T extends UserAndAdminModel> extends IBaseDao<T>{

    /**
     * 根据手机号查找用户
     */
    T findByMobile(String mobile);

    /**
     * 根据邮箱查找用户
     */
    T findByEmail(String email);

    /**
     * 根据账号查询用户
     */
    T findByUsername(String username);
}

package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_admin;

public interface AdminService extends IBaseService<t_admin>{

    /**
     * 后台用户登陆
     */
    Message<t_admin> login(String username, String password,String ipAddr);

    /**
     * 创建管理员
     */
    Message<String> addAdmin(t_admin admin,long createid);

}

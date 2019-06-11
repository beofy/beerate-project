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
    Message<t_admin> addAdmin(t_admin admin,long createId);

    /**
     * 修改管理员密码
     */
    Message<t_admin> updateAdminPassWord(String oldPwd,String newPwd ,long adminId);


    /**
     * 更新管理员资料
     */
    Message<t_admin> updateAdmin(t_admin admin,long adminId);

}

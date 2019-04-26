package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_user;

public interface UserService extends IBaseService<t_user> {

    /**
     * 注册
     */
    Message<t_user> registe(String mobile ,String password,String registeIp ,String registeChannel);

}

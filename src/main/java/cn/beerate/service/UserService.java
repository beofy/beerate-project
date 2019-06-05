package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_user;

public interface UserService extends IBaseService<t_user> {

    /**
     * @param mobile 手机号
     * @param password 密码
     * @param registeIp 注册ip
     * @param registeChannel 注册渠道
     */
    Message<t_user> registe(String mobile ,String password,String registeIp ,String registeChannel);


    /**
     *
     * @param mobile 手机号
     * @param password 密码
     * @param loginIp 登录ip
     * @param loginChannel 登录渠道
     */
    Message<t_user> login(String mobile,String password,String loginIp,String loginChannel);
}

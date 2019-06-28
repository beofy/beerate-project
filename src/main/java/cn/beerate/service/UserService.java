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
    Message<t_user> registe(String mobile ,String password,String registeIp ,String registeChannel,String sessionId);

    /**
     *
     * @param mobile 手机号
     * @param password 密码
     * @param loginIp 登录ip
     * @param loginChannel 登录渠道
     */
    Message<t_user> login(String mobile,String password,String loginIp,String loginChannel,String sessionId);

    /**
     *
     * @param photo 头像途径
     * @param userId 用户id
     */
    Message<t_user> updateUserPhoto(String photo,long userId);

    /**
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param userId 用户id
     */
    Message<t_user> updateUserPassWord(String oldPwd,String newPwd, long userId);

    /**
     *
     * @param mobile 手机号
     * @param userId 用户id
     */
    Message<t_user> updateUserMobile(String mobile,long userId);

    /**
     *
     * @param email 邮箱
     * @param userId 用户id
     */
    Message<t_user> updateUserEmail(String email,long userId);


    /**
     *
     * @param mobile 用户手机号
     * @param newPwd 新密码
     */
    Message<t_user> resetUserPassword(String mobile,String newPwd);

}

package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_user_business;

import java.io.InputStream;

public interface UserBusinessService extends IBaseService<t_user_business>{

    /**
     *解析名片
     */
    t_user_business parse(InputStream inputStream);

    /**
     * 添加名片信息
     */
    Message<t_user_business> addBusiness(t_user_business userBusiness , long userId);

}

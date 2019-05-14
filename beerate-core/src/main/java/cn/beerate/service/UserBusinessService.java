package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.InvestPrefer;
import cn.beerate.model.dto.UserBusiness;
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

    /**
     * 补充用户名片资料
     */
    Message<String> supplementUserBusiness(InvestPrefer investPrefer, String aboutText, String workText, long userId);

    /**
     * 查询用户名片详细信息
     */
    Message<UserBusiness> findUserBusinessDetail(long userId);

}

package cn.beerate.dao.impl;


import cn.beerate.model.dto.UserBusiness;

public interface UserBusinessRepository {
    /**
     * 查找用户的名片信息
     */
    UserBusiness findUserBusinessDetailByUser(long userId);

}

package cn.beerate.service.impl;

import cn.beerate.dao.UserInfoDao;
import cn.beerate.model.entity.t_user_info;
import cn.beerate.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserInfoServiceImpl extends BaseServiceImpl<t_user_info> implements UserInfoService {

    private UserInfoDao userInfoDao;
    public UserInfoServiceImpl(UserInfoDao userInfoDao) {
        super(userInfoDao);
        this.userInfoDao=userInfoDao;
    }



}

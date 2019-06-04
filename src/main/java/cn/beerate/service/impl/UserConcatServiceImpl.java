package cn.beerate.service.impl;

import cn.beerate.dao.UserConcatDao;
import cn.beerate.model.entity.t_user_contact;
import cn.beerate.service.UserConcatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserConcatServiceImpl extends BaseServiceImpl<t_user_contact> implements UserConcatService {
    private UserConcatDao userConcatDao;

    public UserConcatServiceImpl(UserConcatDao userConcatDao) {
        super(userConcatDao);
        this.userConcatDao = userConcatDao;
    }
}

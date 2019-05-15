package cn.beerate.service.impl;

import cn.beerate.dao.UserAttentionDao;
import cn.beerate.model.entity.t_user_attention;
import cn.beerate.service.UserAttentionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserAttentionServiceImpl extends BaseServiceImpl<t_user_attention> implements UserAttentionService {

    private UserAttentionDao userAttentionDao;
    public UserAttentionServiceImpl(UserAttentionDao userAttentionDao) {
        super(userAttentionDao);
        this.userAttentionDao = userAttentionDao;
    }
}

package cn.beerate.service.impl;

import cn.beerate.dao.UserVisitorDao;
import cn.beerate.model.entity.t_user_visitor;
import cn.beerate.service.UserVisitorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserVisitorServiceImpl extends BaseServiceImpl<t_user_visitor>  implements UserVisitorService {

    private UserVisitorDao userVisitorDao;
    public UserVisitorServiceImpl(UserVisitorDao userVisitorDao) {
        super(userVisitorDao);
        this.userVisitorDao = userVisitorDao;
    }

}

package cn.beerate.service.impl;

import cn.beerate.common.Message;
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

    @Override
    @Transactional
    public Message<t_user_visitor> addUserVisitor(long userId, long userVisitorId,String ipAddr) {
        if (userId==userVisitorId){
            return Message.error();
        }

        t_user_visitor userVisitor = new t_user_visitor();
        userVisitor.setUserId(userId);
        userVisitor.setVisitorUserId(userVisitorId);
        userVisitor.setIpAddr(ipAddr);
        if (userVisitorDao.save(userVisitor)==null){
            return Message.error("保存失败");
        }

        return Message.success(userVisitor);
    }

}

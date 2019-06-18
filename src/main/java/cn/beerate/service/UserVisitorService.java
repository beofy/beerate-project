package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_user_visitor;

public interface UserVisitorService extends IBaseService<t_user_visitor> {

    Message<t_user_visitor> addUserVisitor(long userId,long userVisitorId,String ipAddr);

}

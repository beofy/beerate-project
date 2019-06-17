package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.UserItemAcceptDao;
import cn.beerate.model.dto.UserItemAccept;
import cn.beerate.model.entity.t_user_item_accept;
import cn.beerate.service.UserItemAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserItemAcceptServiceImpl extends BaseServiceImpl<t_user_item_accept> implements UserItemAcceptService {

    private UserItemAcceptDao userItemAcceptDao;

    @Autowired
    public UserItemAcceptServiceImpl(UserItemAcceptDao userItemAcceptDao) {
        super(userItemAcceptDao);
        this.userItemAcceptDao = userItemAcceptDao;
    }

    @Override
    public Message<t_user_item_accept> acceptItem(long accept_user_id,long delivery_id) {
        t_user_item_accept itemAccept = new t_user_item_accept();
        itemAccept.setUser_id(accept_user_id);
        itemAccept.setUser_item_delivery_id(delivery_id);

        if (userItemAcceptDao.save(itemAccept)==null){
            return Message.error("添加失败");
        }

        return Message.success(itemAccept);
    }

    @Override
    public Page<UserItemAccept> userItemAccept(int page, int size, String column, String order, long userId) {
        return userItemAcceptDao.userItemAccept(getPageable(page,size,column,order),userId);
    }
}

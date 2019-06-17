package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.UserItemDeliveryDao;
import cn.beerate.model.ItemType;
import cn.beerate.model.dto.UserItemDelivery;
import cn.beerate.model.entity.t_user_item_delivery;
import cn.beerate.service.UserItemDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserItemDeliveryServiceImpl extends BaseServiceImpl<t_user_item_delivery> implements UserItemDeliveryService {

    private UserItemDeliveryDao userItemDeliveryDao;

    @Autowired
    public UserItemDeliveryServiceImpl(UserItemDeliveryDao userItemDeliveryDao) {
        super(userItemDeliveryDao);
        this.userItemDeliveryDao = userItemDeliveryDao;
    }

    @Override
    public Message<t_user_item_delivery> addUserItemDelivery(long userId, long itemId,String name,ItemType itemType) {
        t_user_item_delivery userItemDelivery = new t_user_item_delivery();
        userItemDelivery.setUserId(userId);
        userItemDelivery.setItemId(itemId);
        userItemDelivery.setName(name);
        userItemDelivery.setItemType(itemType);

        if (userItemDeliveryDao.save(userItemDelivery)==null){
            return Message.error("添加失败");
        }

        return Message.success(userItemDelivery);
    }

    @Override
    public Page<UserItemDelivery> userItemDelivery(int page, int size, String column, String order, long userId, long userAcceptId) {
        return userItemDeliveryDao.userItemDelivery(getPageable(page,size,column,order),userId,userAcceptId);
    }
}

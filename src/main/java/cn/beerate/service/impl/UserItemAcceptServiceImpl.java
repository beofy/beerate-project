package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.UserItemAcceptDao;
import cn.beerate.model.dto.UserItemAccept;
import cn.beerate.model.entity.t_user_item_accept;
import cn.beerate.model.entity.t_user_item_delivery;
import cn.beerate.service.UserItemAcceptService;
import cn.beerate.service.UserItemDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserItemAcceptServiceImpl extends BaseServiceImpl<t_user_item_accept> implements UserItemAcceptService {

    @Autowired
    private UserItemDeliveryService userItemDeliveryService;

    private UserItemAcceptDao userItemAcceptDao;

    @Autowired
    public UserItemAcceptServiceImpl(UserItemAcceptDao userItemAcceptDao) {
        super(userItemAcceptDao);
        this.userItemAcceptDao = userItemAcceptDao;
    }

    @Override
    public Message<t_user_item_accept> acceptItem(long acceptUserId,long deliveryId,long userId) {
        t_user_item_delivery delivery = userItemDeliveryService.getOne(deliveryId);
        if (delivery==null||delivery.getUserId()!=userId){
            return Message.error("不能投递不属于自己的项目");
        }

        if (userItemAcceptDao.findByAcceptUserIdAndUserItemDeliveryId(acceptUserId,deliveryId)!=null){
            return Message.error("项目投递");
        }

        t_user_item_accept itemAccept = new t_user_item_accept();
        itemAccept.setAcceptUserId(acceptUserId);
        itemAccept.setUserItemDeliveryId(deliveryId);

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

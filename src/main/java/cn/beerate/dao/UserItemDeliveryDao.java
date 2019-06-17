package cn.beerate.dao;

import cn.beerate.dao.impl.UserItemDeliveryRepository;
import cn.beerate.model.entity.t_user_item_delivery;
import org.springframework.stereotype.Repository;

@Repository
public interface UserItemDeliveryDao extends IBaseDao<t_user_item_delivery>, UserItemDeliveryRepository {
}

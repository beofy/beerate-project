package cn.beerate.dao;

import cn.beerate.dao.impl.UserItemAcceptRepository;
import cn.beerate.model.entity.t_user_item_accept;
import org.springframework.stereotype.Repository;

@Repository
public interface UserItemAcceptDao extends IBaseDao<t_user_item_accept>, UserItemAcceptRepository {

    t_user_item_accept findByAcceptUserIdAndUserItemDeliveryId(long acceptUserId , long userItemDeliveryId);
}

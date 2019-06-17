package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.ItemType;
import cn.beerate.model.dto.UserItemDelivery;
import cn.beerate.model.entity.t_user_item_delivery;
import org.springframework.data.domain.Page;

public interface UserItemDeliveryService extends IBaseService<t_user_item_delivery> {

    /**
     * 添加项目投递列表
     */
    Message<t_user_item_delivery> addUserItemDelivery(long userId, long itemId, String name,ItemType itemType);

    /**
     * 查询我的投递列表
     */
    Page<UserItemDelivery> userItemDelivery(int page, int size, String column, String order, long userId, long acceptUserId);
}

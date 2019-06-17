package cn.beerate.dao.impl;

import cn.beerate.model.dto.UserItemDelivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserItemDeliveryRepository {

    /**
     *
     * @param userId 用户id
     * @param accept_user_id 接受者用户id
     */
    Page<UserItemDelivery> userItemDelivery(Pageable pageable, long userId, long accept_user_id);

}

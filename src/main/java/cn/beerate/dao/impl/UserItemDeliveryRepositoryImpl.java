package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.UserItemDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserItemDeliveryRepositoryImpl implements UserItemDeliveryRepository {

    @Autowired
    private GenericRepository genericRepository;

    public Page<UserItemDelivery> userItemDelivery(Pageable pageable, long userId, long acceptUserId) {
        String querySql = "SELECT delivery.`id`, delivery.`userId`, delivery.`itemId`, delivery.`name`, delivery.`itemType`, ( SELECT count(1) FROM t_user_item_accept accept WHERE accept.id = delivery.id AND accept.acceptUserId = :acceptUserId ) AS `isDelivery` FROM t_user_item_delivery delivery WHERE userId = :userId";
        String countSql = "SELECT COUNT(1) FROM t_user_item_delivery delivery WHERE userId = :userId";

        Map<String, Object> args = new HashMap<>();
        args.put("userId", userId);
        args.put("acceptUserId", acceptUserId);

        return genericRepository.getPage(querySql, countSql, args, pageable, UserItemDelivery.class);
    }
}

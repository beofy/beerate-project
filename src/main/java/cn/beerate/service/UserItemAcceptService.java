package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.UserItemAccept;
import cn.beerate.model.entity.t_user_item_accept;
import org.springframework.data.domain.Page;

public interface UserItemAcceptService extends IBaseService<t_user_item_accept> {

    /**
     * 接收项目
     */
    Message<t_user_item_accept> acceptItem(long AcceptUserId,long deliveryId);


    /**
     * 我收到的项目
     */
    Page<UserItemAccept> userItemAccept(int page, int size, String column, String order, long userId);
}

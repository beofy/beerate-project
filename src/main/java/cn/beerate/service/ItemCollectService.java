package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.ItemType;
import cn.beerate.model.dto.UserItemCollect;
import cn.beerate.model.entity.t_item_collect;
import org.springframework.data.domain.Page;

public interface ItemCollectService extends IBaseService<t_item_collect> {

    /**
     * 添加收藏
     */
    Message<t_item_collect> addCollect(long userId, long itemId,ItemType itemType);

    /**
     * 取消收藏
     */
    Message<t_item_collect> delCollect(long userId, long itemId, ItemType itemType);

    /**
     * 是否已收藏
     */
    t_item_collect isCollect(long userId, long itemId,ItemType itemType);

    /**
     * 我的收藏
     */
    Page<UserItemCollect> pageOfCollect(int page, int size, String column, String order, long userId);
}

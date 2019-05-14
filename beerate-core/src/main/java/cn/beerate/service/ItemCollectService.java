package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_collect;

public interface ItemCollectService extends IBaseService<t_item_collect> {

    Message<String> addCollect(String itemType, long userId, long itemId);

    Message<String> delCollect(String itemType, long userId, long itemId);

    t_item_collect findCollect(String itemType, long userId, long itemId);

    Message pageOfCollect(int page, int size, String column, String order, long userId, String itemType);

}

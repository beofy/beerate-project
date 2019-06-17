package cn.beerate.dao;

import cn.beerate.dao.impl.ItemCollectRepository;
import cn.beerate.model.ItemType;
import cn.beerate.model.entity.t_item_collect;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCollectDao extends IBaseDao<t_item_collect>, ItemCollectRepository {

    t_item_collect findByUser_idAndItem_idAndItemType(long user_id, long item_id, ItemType itemType);

}

package cn.beerate.dao;

import cn.beerate.dao.impl.ItemCollectRepository;
import cn.beerate.model.entity.t_item_collect;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCollectDao extends IBaseDao<t_item_collect>, ItemCollectRepository {

    t_item_collect findByUserIdAndItemIdAndItemType(long userId, long itemId, String itemType);

}

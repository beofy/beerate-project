package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.ItemCollectDao;
import cn.beerate.model.ItemType;
import cn.beerate.model.dto.UserItemCollect;
import cn.beerate.model.entity.t_item_collect;
import cn.beerate.service.ItemCollectService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemCollectServiceImpl extends BaseServiceImpl<t_item_collect> implements ItemCollectService {
    private ItemCollectDao itemCollectDao;

    public ItemCollectServiceImpl(ItemCollectDao itemCollectDao) {
        super(itemCollectDao);
        this.itemCollectDao = itemCollectDao;
    }

    @Transactional
    @Override
    public Message<t_item_collect> addCollect(long userId, long itemId, ItemType itemType) {
        t_item_collect item_collect = isCollect(userId, itemId,itemType);
        if (item_collect!=null) {
            return Message.error("项目已收藏");
        }

        t_item_collect itemCollect = new t_item_collect();
        itemCollect.setUserId(userId);
        itemCollect.setItemId(itemId);
        itemCollect.setItemType(itemType);

        if (itemCollectDao.save(itemCollect) == null) {
            return Message.error("添加失败");
        }

        return Message.success(itemCollect);
    }

    @Override
    public Page<UserItemCollect> pageOfCollect(int page, int size, String column, String order, long userId) {
        return itemCollectDao.userItemCollect(getPageable(page, size, column, order),userId);
    }


    @Override
    @Transactional
    public Message<t_item_collect> delCollect(long userId, long itemId, ItemType itemType) {
        t_item_collect item_collect = isCollect(userId, itemId,itemType);
        if (item_collect==null) {
            return Message.error("项目未收藏");
        }

        itemCollectDao.delete(item_collect);

        return Message.success(item_collect);
    }

    @Override
    public t_item_collect isCollect(long userId, long itemId, ItemType itemType) {
        return itemCollectDao.findByUserIdAndItemIdAndItemType(userId,itemId,itemType.name());
    }

}

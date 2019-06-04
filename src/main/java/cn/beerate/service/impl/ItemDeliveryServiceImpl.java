package cn.beerate.service.impl;

import cn.beerate.dao.ItemDeliveryDao;
import cn.beerate.model.entity.t_item_delivery;
import cn.beerate.service.ItemDeliveryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ItemDeliveryServiceImpl extends BaseServiceImpl<t_item_delivery> implements ItemDeliveryService {

    private ItemDeliveryDao itemDeliveryDao;
    public ItemDeliveryServiceImpl(ItemDeliveryDao itemDeliveryDao) {
        super(itemDeliveryDao);
        this.itemDeliveryDao = itemDeliveryDao;
    }

}

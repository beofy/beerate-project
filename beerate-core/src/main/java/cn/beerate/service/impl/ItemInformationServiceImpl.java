package cn.beerate.service.impl;

import cn.beerate.dao.ItemInformationDao;
import cn.beerate.model.entity.t_item_information;
import cn.beerate.service.ItemInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ItemInformationServiceImpl extends BaseServiceImpl<t_item_information> implements ItemInformationService {
    private ItemInformationDao itemInformationDao;
    public ItemInformationServiceImpl(ItemInformationDao itemInformationDao) {
        super(itemInformationDao);
        this.itemInformationDao = itemInformationDao;
    }

}

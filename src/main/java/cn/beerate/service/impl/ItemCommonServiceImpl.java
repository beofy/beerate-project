package cn.beerate.service.impl;

import cn.beerate.dao.ItemCommonDao;
import cn.beerate.model.ItemModel;
import cn.beerate.service.ItemCommonService;

public class ItemCommonServiceImpl<T extends ItemModel> extends BaseServiceImpl<T> implements ItemCommonService<T> {

    private ItemCommonDao itemCommonDao;
    public <D extends ItemCommonDao<T>> ItemCommonServiceImpl(D d) {
        super(d);
        this.itemCommonDao = d;
    }

}

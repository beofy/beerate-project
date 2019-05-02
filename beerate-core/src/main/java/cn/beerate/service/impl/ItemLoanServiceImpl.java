package cn.beerate.service.impl;

import cn.beerate.dao.ItemLoanDao;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.ItemLoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ItemLoanServiceImpl extends BaseServiceImpl<t_item_loan> implements ItemLoanService {

    private ItemLoanDao itemLoanDao;
    public ItemLoanServiceImpl(ItemLoanDao itemLoanDao) {
        super(itemLoanDao);
        this.itemLoanDao = itemLoanDao;
    }
}

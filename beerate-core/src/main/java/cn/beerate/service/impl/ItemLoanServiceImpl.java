package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.ItemLoanDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ModelValidate;
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

    public Message<t_item_loan> addItemLoan(t_item_loan itemLoan){
        Message message = ModelValidate.itemLoanValid(itemLoan);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.success(itemLoanDao.save(itemLoan));
    }

    public Message<t_item_loan> addItemLoanByUser(t_item_loan itemLoan,long userId){
        itemLoan.getUser().setId(userId);
        itemLoan.setAuditStatus(AuditStatus.WAIT_AUDIT);

        return addItemLoan(itemLoan);
    }

    public Message<t_item_loan> addItemLoanByAdmin(t_item_loan itemLoan,long adminId){
        itemLoan.getAdmin().setId(adminId);
        itemLoan.setAuditStatus(AuditStatus.PASS_AUDIT);

        return addItemLoan(itemLoan);
    }

}

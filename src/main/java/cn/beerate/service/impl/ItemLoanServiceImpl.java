package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.ItemLoanDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ModelValidate;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.model.entity.t_user;
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

    @Transactional
    public Message<t_item_loan> addItemLoan(t_item_loan itemLoan){
        Message message = ModelValidate.itemLoanValid(itemLoan);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.success(itemLoanDao.save(itemLoan));
    }

    @Transactional
    public Message<t_item_loan> addItemLoanByUser(t_item_loan itemLoan,long userId){
        t_user user = new t_user();
        user.setId(userId);

        itemLoan.setUser(user);
        itemLoan.setAuditStatus(AuditStatus.WAIT_AUDIT);

        return addItemLoan(itemLoan);
    }

    @Transactional
    public Message<t_item_loan> addItemLoanByAdmin(t_item_loan itemLoan,long adminId){
        t_admin admin = new t_admin();
        admin.setId(adminId);

        itemLoan.setAdmin(admin);
        itemLoan.setAuditStatus(AuditStatus.PASS_AUDIT);

        return addItemLoan(itemLoan);
    }

}

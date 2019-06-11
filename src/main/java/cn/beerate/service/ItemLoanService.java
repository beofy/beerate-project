package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.entity.t_item_loan;

public interface ItemLoanService extends ItemCommonService<t_item_loan>{

    Message<t_item_loan> addItemLoan(t_item_loan itemLoan);

    Message<t_item_loan> addItemLoanByUser(t_item_loan itemLoan, long userId);

    Message<t_item_loan> addItemLoanByAdmin(t_item_loan itemLoan, long adminId);

    /**
     * 项目审核
     */
    Message<t_item_loan> auditItemLoan(AuditStatus auditStatus,long itemId);

}

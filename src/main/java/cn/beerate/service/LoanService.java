package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.Loan;
import cn.beerate.model.dto.LoanDetail;
import cn.beerate.model.dto.MyLoan;
import cn.beerate.model.entity.t_item_loan;
import org.springframework.data.domain.Page;

public interface LoanService extends ItemCommonService<t_item_loan>{

    /**
     * 查询我的融资列表
     */
    Page<MyLoan> pageMyLoanByUser(int page, int size, String column, String order,long userId);

    /**
     * 查询项目详情
     */
    LoanDetail LoanDetailByUser(long loanId, long userId);

    /**
     * 更新融资项目信息
     */
    Message<t_item_loan> updateItemByUser(t_item_loan loan, long itemId,long userId) ;

    /**
     * 项目集列表
     */
    Page<Loan> pageLoan(int page, int size, String column, String order);
}

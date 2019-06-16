package cn.beerate.dao.impl;

import cn.beerate.model.dto.LoanDetail;
import cn.beerate.model.dto.MyLoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanRepository {

    /**
     * 查询我的融资列表
     */
    Page<MyLoan> pageMyLoanByUser(Pageable pageable,long userId);

    /**
     * 查询项目详情
     */
    LoanDetail LoanDetailByUser(long loanId,long userId);

}

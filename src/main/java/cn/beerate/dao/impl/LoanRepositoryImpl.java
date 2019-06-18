package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.Loan;
import cn.beerate.model.dto.LoanDetail;
import cn.beerate.model.dto.MyLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoanRepositoryImpl implements LoanRepository{
    @Autowired
    private GenericRepository genericRepository;

    public Page<MyLoan> pageMyLoanByUser(Pageable pageable,long userId){
        String querySql="SELECT id,itemName,industryRealm,amount,period,auditStatus FROM t_item_loan WHERE userId = :userId";
        String countSql="SELECT count(1) FROM t_item_loan WHERE userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("userId",userId);

        return genericRepository.getPage(querySql,countSql,args,pageable,MyLoan.class);
    }

    @Override
    public LoanDetail LoanDetailByUser(long loanId, long userId) {
        String querySql="SELECT id, itemName, companyName, logoUri, industryRealm, companyWebsite, companyIosUrl, companyAndroidUrl, isQuoted, stockCode, amount, amountUnit, purpose, period, periodUnit, repayment, businessProposalUri, businessLicenseUri, financialReportUri, auditReportUri, indebtednessUri, capitalFlowUri, endTime, isUrgent, isPlatformAuthentication, isFirstHandle, auditStatus, description, adminId, userId FROM t_item_loan WHERE  id = :id and userId = :userId ";
        Map<String,Object> args= new HashMap<>();
        args.put("id",loanId);
        args.put("userId",userId);

        return genericRepository.getObject(querySql,args,LoanDetail.class);
    }

    @Override
    public Page<Loan> pageLoan(Pageable pageable) {
        return null;
    }
}

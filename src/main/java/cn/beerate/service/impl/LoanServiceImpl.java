package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.LoanDao;
import cn.beerate.model.*;
import cn.beerate.model.dto.Loan;
import cn.beerate.model.dto.LoanDetail;
import cn.beerate.model.dto.MyLoan;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.model.entity.t_user_item_delivery;
import cn.beerate.service.LoanService;
import cn.beerate.service.UserItemDeliveryService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LoanServiceImpl extends ItemCommonServiceImpl<t_item_loan> implements LoanService {

    private LoanDao loanDao;
    private UserItemDeliveryService userItemDeliveryService;

    public LoanServiceImpl(LoanDao loanDao,UserItemDeliveryService userItemDeliveryService) {
        super(loanDao);
        this.loanDao = loanDao;
        this.userItemDeliveryService=userItemDeliveryService;
    }

    private Message<String> loanValid(t_item_loan loan){
        if(StringUtils.isBlank(loan.getItemName())){
            return Message.error("请输入正确的项目名称");
        }

        if(StringUtils.isBlank(loan.getCompanyName())){
            return Message.error("请输入公司名称");
        }

        if(StringUtils.isBlank(loan.getLogoUri())){
            return Message.error("公司logo不存在");
        }

        if(loan.getIndustryRealm() == IndustryRealm.NONE){
            return Message.error("请选择正确的行业领域");
        }

        if(StringUtils.isBlank(loan.getCompanyWebsite())){
            return Message.error("请输入公司官网");
        }

        if(StringUtils.isBlank(loan.getCompanyIosUrl())){
            return Message.error("请输入ios应用地址");
        }

        if(StringUtils.isBlank(loan.getCompanyAndroidUrl())){
            return Message.error("请输入android应用地址");
        }

        if(loan.getIsQuoted()==null){
            return Message.error("请选择是否上市");
        }

        if(BooleanUtils.isTrue(loan.getIsQuoted())){
            if(StringUtils.isBlank(loan.getStockCode())){
                return Message.error("请输入股票代码");
            }
        }

        if(loan.getAmount()==null||loan.getAmount()<0){
            return Message.error("请输入正确的融资金额");
        }

        if(loan.getAmountUnit()== AmountUnit.NONE){
            return Message.error("请选择金额单位");
        }

        if(StringUtils.isBlank(loan.getPurpose())){
            return Message.error("请输入资金用途");
        }

        if(loan.getPeriod()==null||loan.getPeriod()<0){
            return Message.error("请输入借款期限");
        }

        if(loan.getPeriodUnit()== PeriodUnit.NONE){
            return Message.error("请选择期限单位");
        }

        if(StringUtils.isBlank(loan.getRepayment())){
            return Message.error("请填写还款来源");
        }

        if(StringUtils.isBlank(loan.getBusinessProposalUri())){
            return Message.error("BP计划书不存在");
        }

        if(StringUtils.isBlank(loan.getBusinessLicenseUri())){
            return Message.error("营业执照不存在");
        }

        if(StringUtils.isBlank(loan.getFinancialReportUri())){
            return Message.error("财务报表不存在");
        }

        if(StringUtils.isBlank(loan.getAuditReportUri())){
            return Message.error("审计报告不存在");
        }

        if(StringUtils.isBlank(loan.getIndebtednessUri())){
            return Message.error("负载明细不存在");
        }

        if(StringUtils.isBlank(loan.getCapitalFlowUri())){
            return Message.error("银行流水不存在");
        }

        return Message.ok("检验通过");
    }

    @Override
    @Transactional
    public Message<t_item_loan> addItem(t_item_loan loan) {
        Message message = loanValid(loan);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return super.addItem(loan);
    }

    @Override
    public Message<t_item_loan> addItemByUser(t_item_loan loan, long userId) {
        Message<t_item_loan> message1= super.addItemByUser(loan,userId);

        if (message1.fail()){
            return message1;
        }

        //添加投递项目列表
        t_item_loan itemLoan = message1.getData();
        Message<t_user_item_delivery> message2 = userItemDeliveryService.addUserItemDelivery(itemLoan.getUser().getId(),itemLoan.getId(),itemLoan.getItemName(), ItemType.ITEM_LOAN);
        if (message2.fail()){
            return Message.error(message2.getMsg());
        }

        return message1;
    }

    @Override
    public Page<MyLoan> pageMyLoanByUser(int page, int size, String column, String order,long userId) {
        return loanDao.pageMyLoanByUser(getPageable(page,size,column,order),userId);
    }

    @Override
    public LoanDetail LoanDetailByUser(long loanId, long userId) {
        return loanDao.LoanDetailByUser(loanId,userId);
    }

    @Override
    @Transactional
    public Message<t_item_loan> updateItemByUser(t_item_loan loan, long itemId,long userId) {
        //参数校验
        Message message = loanValid(loan);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        t_item_loan itemLoan = loanDao.findByIdAndUserId(itemId, userId);
        if (itemLoan.getAuditStatus()!= AuditStatus.SUPPLEMENT){
            return Message.error("非补充资料状态");
        }

        //更改信息
        itemLoan.setItemName(loan.getItemName());
        itemLoan.setCompanyName(loan.getCompanyName());
        itemLoan.setLogoUri(loan.getLogoUri());
        itemLoan.setIndustryRealm(loan.getIndustryRealm());
        itemLoan.setCompanyWebsite(loan.getCompanyWebsite());
        itemLoan.setCompanyIosUrl(loan.getCompanyIosUrl());
        itemLoan.setCompanyAndroidUrl(loan.getCompanyAndroidUrl());
        itemLoan.setIsQuoted(loan.getIsQuoted());
        itemLoan.setStockCode(loan.getStockCode());
        itemLoan.setAmount(loan.getAmount());
        itemLoan.setAmountUnit(loan.getAmountUnit());
        itemLoan.setPurpose(loan.getPurpose());
        itemLoan.setPeriod(loan.getPeriod());
        itemLoan.setPeriodUnit(loan.getPeriodUnit());
        itemLoan.setRepayment(loan.getRepayment());
        itemLoan.setBusinessProposalUri(loan.getBusinessProposalUri());
        itemLoan.setBusinessLicenseUri(loan.getBusinessLicenseUri());
        itemLoan.setFinancialReportUri(loan.getFinancialReportUri());
        itemLoan.setAuditReportUri(loan.getAuditReportUri());
        itemLoan.setIndebtednessUri(loan.getIndebtednessUri());
        itemLoan.setCapitalFlowUri(loan.getCapitalFlowUri());

        return super.updateItem(itemLoan);
    }

    @Override
    public Page<Loan> pageLoan(int page, int size, String column, String order) {
        return loanDao.pageLoan(getPageable(page, size, column, order));
    }
}

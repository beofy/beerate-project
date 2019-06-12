package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.LoanDao;
import cn.beerate.model.AmountUnit;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.PeriodUnit;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.LoanService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LoanServiceImpl extends ItemCommonServiceImpl<t_item_loan> implements LoanService {

    private LoanDao itemLoanDao;
    public LoanServiceImpl(LoanDao itemLoanDao) {
        super(itemLoanDao);
        this.itemLoanDao = itemLoanDao;
    }

    private Message<String> itemLoanValid(t_item_loan itemLoan){
        if(StringUtils.isBlank(itemLoan.getItemName())){
            return Message.error("请输入正确的项目名称");
        }

        if(StringUtils.isBlank(itemLoan.getCompanyName())){
            return Message.error("请输入公司名称");
        }

        if(StringUtils.isBlank(itemLoan.getLogoUri())){
            return Message.error("公司logo不存在");
        }

        if(itemLoan.getIndustryRealm() == IndustryRealm.NONE){
            return Message.error("请选择正确的行业领域");
        }

        if(StringUtils.isBlank(itemLoan.getCompanyWebsite())){
            return Message.error("请输入公司官网");
        }

        if(StringUtils.isBlank(itemLoan.getCompanyIosUrl())){
            return Message.error("请输入ios应用地址");
        }

        if(StringUtils.isBlank(itemLoan.getCompanyAndroidUrl())){
            return Message.error("请输入android应用地址");
        }

        if(itemLoan.getIsQuoted()==null){
            return Message.error("请选择是否上市");
        }

        if(BooleanUtils.isTrue(itemLoan.getIsQuoted())){
            if(StringUtils.isBlank(itemLoan.getStockCode())){
                return Message.error("请输入股票代码");
            }
        }

        if(itemLoan.getAmount()==null||itemLoan.getAmount()<0){
            return Message.error("请输入正确的融资金额");
        }

        if(itemLoan.getAmountUnit()== AmountUnit.NONE){
            return Message.error("请选择金额单位");
        }

        if(StringUtils.isBlank(itemLoan.getPurpose())){
            return Message.error("请输入资金用途");
        }

        if(itemLoan.getPeriod()==null||itemLoan.getPeriod()<0){
            return Message.error("请输入借款期限");
        }

        if(itemLoan.getPeriodUnit()== PeriodUnit.NONE){
            return Message.error("请选择期限单位");
        }

        if(StringUtils.isBlank(itemLoan.getRepayment())){
            return Message.error("请填写还款来源");
        }

        if(StringUtils.isBlank(itemLoan.getBusinessProposalUri())){
            return Message.error("BP计划书不存在");
        }

        if(StringUtils.isBlank(itemLoan.getBusinessLicenseUri())){
            return Message.error("营业执照不存在");
        }

        if(StringUtils.isBlank(itemLoan.getFinancialReportUri())){
            return Message.error("财务报表不存在");
        }

        if(StringUtils.isBlank(itemLoan.getAuditReportUri())){
            return Message.error("审计报告不存在");
        }

        if(StringUtils.isBlank(itemLoan.getIndebtednessUri())){
            return Message.error("负载明细不存在");
        }

        if(StringUtils.isBlank(itemLoan.getCapitalFlowUri())){
            return Message.error("银行流水不存在");
        }

        return super.itemModelValid(itemLoan);
    }

    @Override
    @Transactional
    public Message<t_item_loan> addItem(t_item_loan itemLoan) {
        Message message = itemLoanValid(itemLoan);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return super.addItem(itemLoan);
    }
}

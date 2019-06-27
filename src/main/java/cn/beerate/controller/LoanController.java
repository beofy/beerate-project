package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.model.AmountUnit;
import cn.beerate.model.PeriodUnit;
import cn.beerate.model.dto.LoanDetail;
import cn.beerate.model.dto.MyLoan;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.LoanService;
import cn.beerate.utils.PathUtil;
import cn.beerate.utils.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/user/item/loan")
public class LoanController extends UserBaseController {
    private final Log logger = LogFactory.getLog(this.getClass());

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/add")
    public Message add(t_item_loan loan, MultipartFile logo, MultipartFile businessProposal, MultipartFile businessLicense, MultipartFile financialReport, MultipartFile auditReport, MultipartFile indebtedness, MultipartFile capitalFlow) {
        loan.setLogoUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(logo.getOriginalFilename()));
        loan.setBusinessProposalUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(businessProposal.getOriginalFilename()));
        loan.setBusinessLicenseUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(businessLicense.getOriginalFilename()));
        loan.setFinancialReportUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(financialReport.getOriginalFilename()));
        loan.setAuditReportUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(auditReport.getOriginalFilename()));
        loan.setIndebtednessUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(indebtedness.getOriginalFilename()));
        loan.setCapitalFlowUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(capitalFlow.getOriginalFilename()));

        loan.setAmountUnit(AmountUnit.WY);//设置金额单位
        loan.setPeriodUnit(PeriodUnit.MONTH);//设置期限单位


        Message<t_item_loan> message = loanService.addItemByUser(loan, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        try {
            logo.transferTo(new File(PathUtil.getRoot() +loan.getLogoUri()));
            businessProposal.transferTo(new File(PathUtil.getRoot()+ loan.getBusinessProposalUri()));
            businessLicense.transferTo(new File(PathUtil.getRoot() + loan.getBusinessLicenseUri()));
            financialReport.transferTo(new File(PathUtil.getRoot() + loan.getFinancialReportUri()));
            auditReport.transferTo(new File(PathUtil.getRoot() + loan.getAuditReportUri()));
            indebtedness.transferTo(new File(PathUtil.getRoot() + loan.getIndebtednessUri()));
            capitalFlow.transferTo(new File(PathUtil.getRoot()+ loan.getCapitalFlowUri()));
        }catch (IOException ioe){
            logger.error(String.format("文件保存失败,原因：[%s]",ioe.getCause()),ioe);
        }

        return Message.ok("添加成功");
    }

    @PostMapping("/list")
    public Message<Page<MyLoan>> list(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return Message.success(loanService.pageMyLoanByUser(page,size,column,order,getUserId()));
    }

    @PostMapping("/detail")
    public Message<LoanDetail> detail(long loanId) {
        return Message.success(loanService.LoanDetailByUser(loanId,getUserId()));
    }

    @PostMapping("/update")
    public Message<String> update(t_item_loan loan, MultipartFile logo, MultipartFile businessProposal, MultipartFile businessLicense, MultipartFile financialReport, MultipartFile auditReport, MultipartFile indebtedness, MultipartFile capitalFlow ,long loanId) {

        loan.setLogoUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(logo.getOriginalFilename()));
        loan.setBusinessProposalUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(businessProposal.getOriginalFilename()));
        loan.setBusinessLicenseUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(businessLicense.getOriginalFilename()));
        loan.setFinancialReportUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(financialReport.getOriginalFilename()));
        loan.setAuditReportUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(auditReport.getOriginalFilename()));
        loan.setIndebtednessUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(indebtedness.getOriginalFilename()));
        loan.setCapitalFlowUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(capitalFlow.getOriginalFilename()));

        loan.setAmountUnit(AmountUnit.WY);//设置金额单位
        loan.setPeriodUnit(PeriodUnit.MONTH);//设置期限单位

        Message<t_item_loan> message = loanService.updateItemByUser(loan,loanId,getUserId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        try {
            logo.transferTo(new File(PathUtil.getRoot() +loan.getLogoUri()));
            businessProposal.transferTo(new File(PathUtil.getRoot()+ loan.getBusinessProposalUri()));
            businessLicense.transferTo(new File(PathUtil.getRoot() + loan.getBusinessLicenseUri()));
            financialReport.transferTo(new File(PathUtil.getRoot() + loan.getFinancialReportUri()));
            auditReport.transferTo(new File(PathUtil.getRoot() + loan.getAuditReportUri()));
            indebtedness.transferTo(new File(PathUtil.getRoot() + loan.getIndebtednessUri()));
            capitalFlow.transferTo(new File(PathUtil.getRoot()+ loan.getCapitalFlowUri()));
        }catch (IOException ioe){
            logger.error(String.format("文件保存失败,原因：[%s]",ioe.getCause()),ioe);
        }


        return Message.ok("修改成功");
    }

}

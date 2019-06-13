package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.model.AmountUnit;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.PeriodUnit;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.LoanService;
import cn.beerate.utils.PathUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 后台项目融资管理-控制器
 */
@Controller
@RequestMapping("/admin/item/loan")
public class LoanMngController extends AdminBaseController {
    private final Log logger = LogFactory.getLog(this.getClass());

    private LoanService loanService;

    public LoanMngController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(int page, int size,
                           @RequestParam(required = false) String column,
                           @RequestParam(required = false) String order,
                           @RequestParam(required = false) String field,
                           @RequestParam(required = false) String value,
                           @RequestParam(required = false) Date beginDate,
                           @RequestParam(required = false) Date endDate,
                           Model model) {

        model.addAttribute("page",loanService.page(page, size, column, order,field,value,beginDate,endDate));

        return "admin/item/loan/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(Model model) {
        model.addAttribute("industryRealms", IndustryRealm.values());

        return "admin/item/loan/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_loan loan, MultipartFile logo, MultipartFile businessProposal, MultipartFile businessLicense, MultipartFile financialReport, MultipartFile auditReport, MultipartFile indebtedness, MultipartFile capitalFlow)  {

        loan.setLogoUri(PropertiesHolder.ATTACHMENT_PATH + logo.getOriginalFilename());
        loan.setBusinessProposalUri(PropertiesHolder.ATTACHMENT_PATH + businessProposal.getOriginalFilename());
        loan.setBusinessLicenseUri(PropertiesHolder.ATTACHMENT_PATH + businessLicense.getOriginalFilename());
        loan.setFinancialReportUri(PropertiesHolder.ATTACHMENT_PATH+ financialReport.getOriginalFilename());
        loan.setAuditReportUri(PropertiesHolder.ATTACHMENT_PATH + auditReport.getOriginalFilename());
        loan.setIndebtednessUri(PropertiesHolder.ATTACHMENT_PATH+ indebtedness.getOriginalFilename());
        loan.setCapitalFlowUri(PropertiesHolder.ATTACHMENT_PATH + capitalFlow.getOriginalFilename());

        loan.setAmountUnit(AmountUnit.WY);//设置金额单位
        loan.setPeriodUnit(PeriodUnit.MONTH);//设置期限单位

        Message<t_item_loan> message = loanService.addItemByAdmin(loan, getAdminId());
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

    /**
     * 项目详情
     */
    @GetMapping("/detail.html")
    public String detail(long itemId,Model model){
        model.addAttribute("item",loanService.getOne(itemId));
        model.addAttribute("auditStatus", AuditStatus.values());
        return "admin/item/loan/detail";
    }

}

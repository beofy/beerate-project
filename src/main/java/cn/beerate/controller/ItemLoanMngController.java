package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.model.AmountUnit;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.PeriodUnit;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.ItemLoanService;
import cn.beerate.utils.PathUtil;
import org.apache.commons.lang3.EnumUtils;
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
@RequestMapping("/admin/itemloan")
public class ItemLoanMngController extends AdminBaseController {
    private final Log logger = LogFactory.getLog(this.getClass());

    private ItemLoanService itemLoanService;

    public ItemLoanMngController(ItemLoanService itemLoanService) {
        this.itemLoanService = itemLoanService;
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

        model.addAttribute("page",itemLoanService.page(page, size, column, order,field,value,beginDate,endDate));

        return "itemloan/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(Model model) {
        model.addAttribute("industryRealms", IndustryRealm.values());

        return "itemloan/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_loan itemLoan, MultipartFile logo, MultipartFile businessProposal, MultipartFile businessLicense, MultipartFile financialReport, MultipartFile auditReport, MultipartFile indebtedness, MultipartFile capitalFlow)  {

        itemLoan.setLogoUri(PropertiesHolder.ATTACHMENT_PATH + logo.getOriginalFilename());
        itemLoan.setBusinessProposalUri(PropertiesHolder.ATTACHMENT_PATH + businessProposal.getOriginalFilename());
        itemLoan.setBusinessLicenseUri(PropertiesHolder.ATTACHMENT_PATH + businessLicense.getOriginalFilename());
        itemLoan.setFinancialReportUri(PropertiesHolder.ATTACHMENT_PATH+ financialReport.getOriginalFilename());
        itemLoan.setAuditReportUri(PropertiesHolder.ATTACHMENT_PATH + auditReport.getOriginalFilename());
        itemLoan.setIndebtednessUri(PropertiesHolder.ATTACHMENT_PATH+ indebtedness.getOriginalFilename());
        itemLoan.setCapitalFlowUri(PropertiesHolder.ATTACHMENT_PATH + capitalFlow.getOriginalFilename());

        itemLoan.setAmountUnit(AmountUnit.WY);//设置金额单位
        itemLoan.setPeriodUnit(PeriodUnit.MONTH);//设置期限单位

        Message<t_item_loan> message = itemLoanService.addItemByAdmin(itemLoan, getAdminId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        try {
            logo.transferTo(new File(PathUtil.getRoot() +itemLoan.getLogoUri()));
            businessProposal.transferTo(new File(PathUtil.getRoot()+ itemLoan.getBusinessProposalUri()));
            businessLicense.transferTo(new File(PathUtil.getRoot() + itemLoan.getBusinessLicenseUri()));
            financialReport.transferTo(new File(PathUtil.getRoot() + itemLoan.getFinancialReportUri()));
            auditReport.transferTo(new File(PathUtil.getRoot() + itemLoan.getAuditReportUri()));
            indebtedness.transferTo(new File(PathUtil.getRoot() + itemLoan.getIndebtednessUri()));
            capitalFlow.transferTo(new File(PathUtil.getRoot()+ itemLoan.getCapitalFlowUri()));
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
        model.addAttribute("item",itemLoanService.getOne(itemId));
        model.addAttribute("auditStatus", AuditStatus.values());
        return "itemloan/detail";
    }

    /**
     * 项目审核
     */
    @PostMapping("/audit")
    @ResponseBody
    public Message<String> detail(String auditStatus,String description,long itemId){
        Message<t_item_loan> message =itemLoanService.auditItem(EnumUtils.getEnumIgnoreCase(AuditStatus.class,auditStatus),description,itemId);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("审核成功");
    }


}

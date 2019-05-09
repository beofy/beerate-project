package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.model.AmountUnit;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.PeriodUnit;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.oss.OSS;
import cn.beerate.service.ItemLoanService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 后台项目融资管理-控制器
 */
@Controller
@RequestMapping("/admin/itemloan")
public class ItemLoanMngController extends AdminBaseController {
    private final Log logger = LogFactory.getLog(this.getClass());

    private ItemLoanService itemLoanService;
    private OSS oss;

    public ItemLoanMngController(ItemLoanService itemLoanService, OSS oss) {
        this.itemLoanService = itemLoanService;
        this.oss = oss;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage() {

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

        itemLoan.setLogoUri("/" + PropertiesHolder.properties.getFileProperties().getAdminFile() + UUID.randomUUID().toString());
        itemLoan.setBusinessProposalUri("/" + PropertiesHolder.properties.getFileProperties().getAdminFile() + UUID.randomUUID().toString());
        itemLoan.setBusinessLicenseUri("/" + PropertiesHolder.properties.getFileProperties().getAdminFile() + UUID.randomUUID().toString());
        itemLoan.setFinancialReportUri("/" + PropertiesHolder.properties.getFileProperties().getAdminFile() + UUID.randomUUID().toString());
        itemLoan.setAuditReportUri("/" + PropertiesHolder.properties.getFileProperties().getAdminFile() + UUID.randomUUID().toString());
        itemLoan.setIndebtednessUri("/" + PropertiesHolder.properties.getFileProperties().getAdminFile() + UUID.randomUUID().toString());
        itemLoan.setCapitalFlowUri("/" + PropertiesHolder.properties.getFileProperties().getAdminFile() + UUID.randomUUID().toString());

        itemLoan.setAmountUnit(AmountUnit.WY);//设置金额单位
        itemLoan.setPeriodUnit(PeriodUnit.MONTH);//设置期限单位

        Message<t_item_loan> message = itemLoanService.addItemLoanByAdmin(itemLoan, getAdminId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        try {
            oss.uploadFile(logo.getInputStream(), oss.getRoot() + itemLoan.getLogoUri());
            oss.uploadFile(businessProposal.getInputStream(), oss.getRoot() + itemLoan.getBusinessProposalUri());
            oss.uploadFile(businessLicense.getInputStream(), oss.getRoot() + itemLoan.getBusinessLicenseUri());
            oss.uploadFile(financialReport.getInputStream(), oss.getRoot() + itemLoan.getFinancialReportUri());
            oss.uploadFile(auditReport.getInputStream(), oss.getRoot() + itemLoan.getAuditReportUri());
            oss.uploadFile(indebtedness.getInputStream(), oss.getRoot() + itemLoan.getIndebtednessUri());
            oss.uploadFile(capitalFlow.getInputStream(), oss.getRoot() + itemLoan.getCapitalFlowUri());
        }catch (IOException ioe){
            logger.error(String.format("文件保存失败,原因：[%s]",ioe.getCause()),ioe);
        }


        return Message.ok("添加成功");
    }

}

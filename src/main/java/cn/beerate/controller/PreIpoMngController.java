package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.service.PreIpoService;
import cn.beerate.utils.PathUtil;
import cn.beerate.utils.StringUtil;
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
 * 后台PRE-IPO管理-控制器
 */
@Controller
@RequestMapping("/admin/item/preipo")
public class PreIpoMngController extends AdminBaseController {

    private final Log logger = LogFactory.getLog(this.getClass());
    private PreIpoService preIpoService;

    public PreIpoMngController(PreIpoService preIpoService) {
        this.preIpoService = preIpoService;
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

        model.addAttribute("page", preIpoService.page(page, size, column, order, field, value, beginDate, endDate));

        return "admin/item/preipo/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(Model model) {
        model.addAttribute("industryRealms", IndustryRealm.values());

        return "admin/item/preipo/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_pre_ipo preIpo, MultipartFile businessProposal) {

        preIpo.setBusinessProposalUri(PropertiesHolder.ATTACHMENT_PATH + StringUtil.generateFileName(businessProposal.getOriginalFilename()));

        Message<t_item_pre_ipo> message = preIpoService.addItemByAdmin(preIpo, getAdminId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        try {
            businessProposal.transferTo(new File(PathUtil.getRoot() + preIpo.getBusinessProposalUri()));
        } catch (IOException ioe) {
            logger.error(String.format("文件保存失败,原因：[%s]", ioe.getCause()), ioe);
        }

        return Message.ok("添加成功");
    }

    /**
     * 项目详情
     */
    @GetMapping("/detail.html")
    public String detail(long preIpoId, Model model) {
        model.addAttribute("preIpo", preIpoService.getOne(preIpoId));
        model.addAttribute("auditStatus", AuditStatus.values());

        return "admin/item/preipo/detail";
    }

}

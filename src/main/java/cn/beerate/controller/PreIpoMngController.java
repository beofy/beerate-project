package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.service.PreIpoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 后台PRE-IPO管理-控制器
 */
@Controller
@RequestMapping("/admin/item/preipo")
public class PreIpoMngController  extends AdminBaseController  {

    private final Log logger = LogFactory.getLog(this.getClass());
    private PreIpoService preIpoService;

    public PreIpoMngController(PreIpoService preIpoService) {
        this.preIpoService = preIpoService;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(){


        return "admin/item/preipo/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(Model model){
        model.addAttribute("industryRealms", IndustryRealm.values());

        return "admin/item/preipo/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_pre_ipo preIpo, MultipartFile businessProposal){

        preIpo.setBusinessProposalUri("/attachment/"+ UUID.randomUUID().toString());

        Message<t_item_pre_ipo> message  = preIpoService.addItemByAdmin(preIpo,getAdminId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        try {
            businessProposal.transferTo(new File(ResourceUtils.getURL("target/classes/static").getPath()+preIpo.getBusinessProposalUri()));
        }catch (IOException ioe){
            logger.error(String.format("文件保存失败,原因：[%s]",ioe.getCause()),ioe);
        }

        return Message.ok("添加成功");
    }

}

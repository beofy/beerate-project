package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.oss.OSS;
import cn.beerate.service.PreIpoService;
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
 * 后台PRE-IPO管理-控制器
 */
@Controller
@RequestMapping("/admin/preipo")
public class PreIpoMngController  extends AdminBaseController  {

    private final Log logger = LogFactory.getLog(this.getClass());
    private PreIpoService preIpoService;
    private OSS oss;

    public PreIpoMngController(PreIpoService preIpoService, OSS oss) {
        this.preIpoService = preIpoService;
        this.oss = oss;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(){


        return "preipo/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(Model model){
        model.addAttribute("industryRealms", IndustryRealm.values());

        return "preipo/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_pre_ipo preIpo, MultipartFile businessProposal){

        preIpo.setBusinessProposalUri("/"+ PropertiesHolder.properties.getFileProperties().getAdminFile()+ UUID.randomUUID().toString());

        Message<t_item_pre_ipo> message  = preIpoService.addPreIpoByAdmin(preIpo,getAdminId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        try {
            oss.uploadFile(businessProposal.getInputStream(),oss.getRoot()+preIpo.getBusinessProposalUri());
        }catch (IOException ioe){
            logger.error(String.format("文件保存失败,原因：[%s]",ioe.getCause()),ioe);
        }

        return Message.ok("添加成功");
    }

}

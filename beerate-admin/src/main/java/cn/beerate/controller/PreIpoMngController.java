package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.service.PreIpoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台PRE-IPO管理-控制器
 */
@Controller
@RequestMapping("/admin/preipo")
public class PreIpoMngController  extends AdminBaseController  {

    private PreIpoService preIpoService;
    public PreIpoMngController(PreIpoService preIpoService) {
        this.preIpoService = preIpoService;
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
    public Message<String> add(t_item_pre_ipo preIpo){

        Message<t_item_pre_ipo> message  = preIpoService.addPreIpoByAdmin(preIpo,getAdminId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

}

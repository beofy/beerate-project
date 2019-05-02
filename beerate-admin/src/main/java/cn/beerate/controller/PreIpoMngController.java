package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.service.PreIpoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String addPage(){
        return "preipo/add";
    }

    /**
     * 项目添加
     */
    public Message<String> add(t_item_pre_ipo preIpo){



        return Message.ok("添加成功");
    }

}

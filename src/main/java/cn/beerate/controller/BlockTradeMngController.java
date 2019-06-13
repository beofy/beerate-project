package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.service.BlockTradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 后台大宗交易管理-控制器
 */
@Controller
@RequestMapping("/admin/item/blocktrade")
public class BlockTradeMngController  extends AdminBaseController {

    private BlockTradeService blockTradeService;
    public BlockTradeMngController(BlockTradeService blockTradeService) {
        this.blockTradeService = blockTradeService;
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
                           Model model){
        model.addAttribute("page",blockTradeService.page(page, size, column, order, field,value,beginDate,endDate));

        return "admin/item/blocktrade/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(){
        return "admin/item/blocktrade/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_block_trade blockTrade){

        Message<t_item_block_trade> message = blockTradeService.addItemByAdmin(blockTrade,getAdminId());
        if(message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    /**
     * 项目详情
     */
    @GetMapping("/detail.html")
    public String detail(long blockTradeId,Model model){
        model.addAttribute("blockTrade",blockTradeService.getOne(blockTradeId));
        model.addAttribute("auditStatus", AuditStatus.values());

        return "admin/item/blocktrade/detail";
    }
}

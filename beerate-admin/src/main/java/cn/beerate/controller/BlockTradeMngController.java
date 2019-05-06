package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.CreditIdentification;
import cn.beerate.model.ModelValidate;
import cn.beerate.model.UnderWeightIdentification;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.service.BlockTradeService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 后台大宗交易管理-控制器
 */
@Controller
@RequestMapping("/admin/blocktrade")
public class BlockTradeMngController  extends AdminBaseController {

    private BlockTradeService blockTradeService;
    public BlockTradeMngController(BlockTradeService blockTradeService) {
        this.blockTradeService = blockTradeService;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(){

        return "blocktrade/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(){
        return "blocktrade/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    public Message<String> add(t_item_block_trade blockTrade){

        Message message = blockTradeService.addBlockTradeByAdmin(blockTrade,getAdminId());
        if(message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

}

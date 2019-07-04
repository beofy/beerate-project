package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.BlockTradeDetail;
import cn.beerate.model.dto.MyBlockTrade;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.service.BlockTradeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/item/blocktrade")
public class BlockTradeController extends UserBaseController {

    private BlockTradeService blockTradeService;

    public BlockTradeController(BlockTradeService blockTradeService) {
        this.blockTradeService = blockTradeService;
    }

    @PostMapping("/add")
    public Message<String> add(t_item_block_trade blockTrade) {
        Message<t_item_block_trade> message = blockTradeService.addItemByUser(blockTrade, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @PostMapping("/list")
    public Message<Page<MyBlockTrade>> list(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return Message.success(blockTradeService.pageMyBlockTradeUser(page,size,column,order,getUserId()));
    }

    @PostMapping("/detail")
    public Message<BlockTradeDetail> detail(long blockTradeId) {
        return Message.success(blockTradeService.BlockTradeDetailByUser(blockTradeId,getUserId()));
    }

    @PostMapping("/update")
    public Message<String> update(t_item_block_trade blockTrade, long itemId){
        Message<t_item_block_trade> message = blockTradeService.updateItemByUser(blockTrade,itemId,getUserId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("修改成功");
    }

}

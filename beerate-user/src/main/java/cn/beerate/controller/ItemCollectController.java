package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_collect;
import cn.beerate.service.ItemCollectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/itemcollect")
public class ItemCollectController extends UserBaseController{

    private ItemCollectService itemCollectService;
    public ItemCollectController(ItemCollectService itemCollectService) {
        this.itemCollectService = itemCollectService;
    }

    @PostMapping("/add")
    public Message<String> add(String itemType,long itemId){
        t_item_collect itemCollect = itemCollectService.findCollect(itemType,getUserId(),itemId);
        if (itemCollect!=null){
            return Message.error("项目已收藏");
        }

       return itemCollectService.addCollect(itemType,getUserId(),itemId);
    }

    @PostMapping("/del")
    public Message<String> del(String itemType,long itemId){
        t_item_collect itemCollect = itemCollectService.findCollect(itemType,getUserId(),itemId);
        if (itemCollect==null){
            return Message.error("项目未收藏");
        }

       return itemCollectService.delCollect(itemType,getUserId(),itemId);
    }

    @PostMapping("/findCollect")
    public Message<Boolean> status(String itemType,long itemId){
        t_item_collect itemCollect = itemCollectService.findCollect(itemType,getUserId(),itemId);
        if (itemCollect==null){
            Message.success(false);
        }

        return Message.success(true);
    }

    @GetMapping("/list")
    public Message list(int page, int size, String column, String order,String itemType){
        return itemCollectService.pageOfCollect(page,size,column,order,getUserId(),itemType);
    }

}

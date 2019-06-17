package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.ItemType;
import cn.beerate.model.entity.t_item_collect;
import cn.beerate.service.ItemCollectService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/item/collect")
public class ItemCollectController extends UserBaseController {
    private ItemCollectService itemCollectService;

    public ItemCollectController(ItemCollectService itemCollectService) {
        this.itemCollectService = itemCollectService;
    }

    @PostMapping("/add")
    public Message<String> add(String itemType, long itemId) {
        ItemType itemTypeEum = EnumUtils.getEnumIgnoreCase(ItemType.class, itemType);
        if (itemTypeEum == null) {
            return Message.error("项目类型错误");
        }

        Message<t_item_collect> message = itemCollectService.addCollect(getUserId(), itemId, itemTypeEum);
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("收藏成功");
    }

    @PostMapping("/del")
    public Message<String> del(String itemType, long itemId) {
        ItemType itemTypeEum = EnumUtils.getEnumIgnoreCase(ItemType.class, itemType);
        if (itemTypeEum == null) {
            return Message.error("项目类型错误");
        }

        Message<t_item_collect> message = itemCollectService.delCollect(getUserId(), itemId, itemTypeEum);
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("删除成功");
    }

    @PostMapping("/isCollect")
    public Message<Boolean> isCollect(String itemType, long itemId) {
        ItemType itemTypeEum = EnumUtils.getEnumIgnoreCase(ItemType.class, itemType);
        if (itemTypeEum == null) {
            return Message.error("项目类型错误");
        }

        t_item_collect itemCollect = itemCollectService.isCollect(getUserId(), itemId, itemTypeEum);
        if (itemCollect == null) {
            return Message.success(false);
        }

        return Message.success(true);
    }

    @GetMapping("/list")
    public Message list(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order, String itemType) {
        ItemType itemTypeEum = EnumUtils.getEnumIgnoreCase(ItemType.class, itemType);
        if (itemTypeEum == null) {
            return Message.error("项目类型错误");
        }

        return Message.success(itemCollectService.pageOfCollect(page, size, column, order, getUserId()));
    }
}

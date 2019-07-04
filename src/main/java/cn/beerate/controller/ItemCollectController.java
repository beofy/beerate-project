package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.ItemType;
import cn.beerate.model.dto.UserItemCollect;
import cn.beerate.model.entity.t_item_collect;
import cn.beerate.service.ItemCollectService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        return Message.ok("取消收藏成功");
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

    @PostMapping("/list")
    public Message<Page<UserItemCollect>> list(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return Message.success(itemCollectService.pageOfCollect(page, size, column, order, getUserId()));
    }
}

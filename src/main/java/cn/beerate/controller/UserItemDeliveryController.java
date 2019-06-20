package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.UserItemAccept;
import cn.beerate.model.dto.UserItemDelivery;
import cn.beerate.model.entity.t_user_item_accept;
import cn.beerate.service.UserItemAcceptService;
import cn.beerate.service.UserItemDeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/item/delivery")
public class UserItemDeliveryController extends UserBaseController {

    private UserItemDeliveryService userItemDeliveryService;
    private UserItemAcceptService userItemAcceptService;

    public UserItemDeliveryController(UserItemDeliveryService userItemDeliveryService, UserItemAcceptService userItemAcceptService) {
        this.userItemDeliveryService = userItemDeliveryService;
        this.userItemAcceptService = userItemAcceptService;
    }

    /**
     * 我的投递列表
     */
    @PostMapping("/list")
    public Message<Page<UserItemDelivery>> list(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order, long userAcceptId) {
        if (userAcceptId==getUserId()){
            return Message.error("不能给自己投递项目");
        }

        return Message.success(userItemDeliveryService.userItemDelivery(page, size, column, order, getUserId(), userAcceptId));
    }

    /**
     * 项目投递(认证的用户)
     */
    @PostMapping("/delivery")
    public Message<String> delivery(long userAcceptId, long deliveryId) {
        Message<t_user_item_accept> message = userItemAcceptService.acceptItem(userAcceptId, deliveryId);
        if (message.fail()) {
            return Message.success(message.getMsg());
        }

        return Message.ok("投递成功");
    }

    /**
     * 收到的项目
     */
    @PostMapping("/accept")
    public Message<Page<UserItemAccept>> accept(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return Message.success(userItemAcceptService.userItemAccept(page, size, column, order, getUserId()));
    }

}
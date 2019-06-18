package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_overseas_listing;
import cn.beerate.service.OverseasListingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/item/overseas")
public class OverseasListingController{

    private OverseasListingService overseasListingService;
    public OverseasListingController(OverseasListingService overseasListingService) {
        this.overseasListingService = overseasListingService;
    }

    @PostMapping("/add")
    public Message<String> add(t_item_overseas_listing overseasListing){
       Message<t_item_overseas_listing> message = overseasListingService.addOverseasListing(overseasListing);
       if (message.fail()){
           return Message.error(message.getMsg());
       }

       return Message.ok("添加成功");
    }
}

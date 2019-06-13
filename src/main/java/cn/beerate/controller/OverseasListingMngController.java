package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_overseas_listing;
import cn.beerate.service.OverseasListingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台海外上市管理-控制器
 */
@Controller
@RequestMapping("/admin/item/overseaslisting")
public class OverseasListingMngController extends AdminBaseController {

    private OverseasListingService overseasListingService;

    public OverseasListingMngController(OverseasListingService overseasListingService) {
        this.overseasListingService = overseasListingService;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(){

        return "admin/item/overseaslisting/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(){
        return "admin/item/overseaslisting/add";
    }

    /**
     * 项目添加
     */
    public Message<String> add(t_item_overseas_listing overseasListing){

        Message<t_item_overseas_listing> message = overseasListingService.addOverseasListing(overseasListing);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }
}

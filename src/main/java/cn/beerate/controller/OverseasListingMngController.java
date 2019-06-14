package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.NetProfit;
import cn.beerate.model.entity.t_item_overseas_listing;
import cn.beerate.service.OverseasListingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public String list(int page, int size,
                       @RequestParam(required = false) String column,
                       @RequestParam(required = false) String order,
                       @RequestParam(required = false) String field,
                       @RequestParam(required = false) String value,
                       @RequestParam(required = false) Date beginDate,
                       @RequestParam(required = false) Date endDate,
                       Model model){
        model.addAttribute("page",overseasListingService.page(page, size, column, order, field,value,beginDate,endDate));

        return "admin/item/overseaslisting/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String add(Model model){
        model.addAttribute("netProfits", NetProfit.values());

        return "admin/item/overseaslisting/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_overseas_listing overseasListing){
        Message<t_item_overseas_listing> message = overseasListingService.addOverseasListing(overseasListing);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @PostMapping("/show")
    @ResponseBody
    public Message<String> editIsShow(long overseasListingId){
        Message<t_item_overseas_listing> message =overseasListingService.editItemIsShow(overseasListingId);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("设置成功");
    }
}

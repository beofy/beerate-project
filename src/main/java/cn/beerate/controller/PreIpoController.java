package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.PreIpoDetail;
import cn.beerate.model.dto.MyPreIpo;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.service.PreIpoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/item/preipo/")
public class PreIpoController extends UserBaseController {

    private PreIpoService preIpoService;

    public PreIpoController(PreIpoService preIpoService) {
        this.preIpoService = preIpoService;
    }

    @PostMapping("/add")
    public Message add(t_item_pre_ipo preIpo) {
        Message<t_item_pre_ipo> message = preIpoService.addItemByUser(preIpo, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @GetMapping("/list")
    public Message<Page<MyPreIpo>> list(int page, int size, String column, String order) {
        return Message.success(preIpoService.pageMyPreIpoUser(page,size,column,order,getUserId()));
    }

    @PostMapping("/detail")
    public Message<PreIpoDetail> detail(long preIpoId) {
        return Message.success(preIpoService.preIpoDetailByUser(preIpoId,getUserId()));
    }

    @PostMapping("/update")
    public Message<String> update(t_item_pre_ipo preIpo, long itemId){
        Message<t_item_pre_ipo> message = preIpoService.updateItemByUser(preIpo,itemId,getUserId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("修改成功");
    }
}

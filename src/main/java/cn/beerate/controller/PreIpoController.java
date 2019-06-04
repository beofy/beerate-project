package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.PreIpo;
import cn.beerate.model.dto.PreIpoList;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.service.PreIpoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/preipo/")
public class PreIpoController extends UserBaseController {

    private PreIpoService preIpoService;

    public PreIpoController(PreIpoService preIpoService) {
        this.preIpoService = preIpoService;
    }

    @PostMapping("/add")
    public Message add(t_item_pre_ipo preIpo) {
        Message<t_item_pre_ipo> message = preIpoService.addPreIpoByUser(preIpo, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @GetMapping("/list")
    public Message<Page<PreIpoList>> list(int page, int size, String column, String order) {


        Page<PreIpoList> pageBean = null;

        return Message.success(pageBean);
    }

    @PostMapping("/detail")
    public Message<PreIpo> detail(long preIpoId) {


        PreIpo pre =null;

        return Message.success(pre);
    }
}

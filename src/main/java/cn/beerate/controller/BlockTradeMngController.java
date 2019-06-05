package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.service.BlockTradeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台大宗交易管理-控制器
 */
@Controller
@RequestMapping("/admin/blocktrade")
public class BlockTradeMngController  extends AdminBaseController {

    private BlockTradeService blockTradeService;
    public BlockTradeMngController(BlockTradeService blockTradeService) {
        this.blockTradeService = blockTradeService;
    }

    /**
     * 列表页
     */
    @GetMapping("/list.html")
    public String listPage(int page, int size,
                           @RequestParam(required = false) String column,
                           @RequestParam(required = false) String order,
                           @RequestParam(required = false) String field,
                           @RequestParam(required = false) String value,
                           @RequestParam(required = false) Date beginDate,
                           @RequestParam(required = false) Date endDate,
                           Model model){

        Page<t_item_block_trade> pageBean = blockTradeService.page(page, size, column, order, (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<t_item_block_trade,t_admin> adminJoin = root.join("admin", JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(adminJoin.get("id"),getAdminId()));

            if(beginDate!=null&&endDate!=null&&beginDate.before(endDate)){
                predicates.add(criteriaBuilder.between(root.get("createTime") , beginDate, endDate));
            }

            if(StringUtils.isNotBlank(field)){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get(field),"%"+value+"%")));
            }

            Predicate[] predicate = new Predicate[predicates.size()];

            return criteriaBuilder.and(predicates.toArray(predicate));
        });

        model.addAttribute("page",pageBean);

        return "blocktrade/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add.html")
    public String addPage(){
        return "blocktrade/add";
    }

    /**
     * 项目添加
     */
    @PostMapping("/add")
    @ResponseBody
    public Message<String> add(t_item_block_trade blockTrade){

        Message<t_item_block_trade> message = blockTradeService.addBlockTradeByAdmin(blockTrade,getAdminId());
        if(message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

}
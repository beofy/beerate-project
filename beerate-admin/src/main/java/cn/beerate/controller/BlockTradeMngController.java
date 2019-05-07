package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.service.BlockTradeService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String listPage(int page, int size, String column, String order, Date beginDate, Date endDate, t_item_block_trade blockTrade , Model model){

        Page<t_item_block_trade> pageBean = blockTradeService.page(page, size, column, order, new Specification<t_item_block_trade>() {
            @Override
            public Predicate toPredicate(Root<t_item_block_trade> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                Join<t_item_block_trade,t_admin> adminJoin = root.join("admin", JoinType.LEFT);
                criteriaBuilder.equal(adminJoin.get("id"),getAdminId());

                if(beginDate!=null&&endDate!=null&&beginDate.before(endDate)){
                    predicates.add(criteriaBuilder.between(root.get("createTime") , beginDate, endDate));
                }

                Predicate[] predicate = new Predicate[predicates.size()];

                return criteriaBuilder.and(predicates.toArray(predicate));
            }
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

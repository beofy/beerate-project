package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.ItemLoan;
import cn.beerate.model.dto.ItemLoanList;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.ItemLoanService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user/itemloan")
public class ItemLoanController extends UserBaseController {

    private ItemLoanService itemLoanService;

    public ItemLoanController(ItemLoanService itemLoanService) {
        this.itemLoanService = itemLoanService;
    }

    @PostMapping("/add")
    public Message add(t_item_loan itemLoan) {
        Message<t_item_loan> message = itemLoanService.addItemLoanByUser(itemLoan, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @GetMapping("/list")
    public Message<Page<ItemLoanList>> list(int page, int size, String column, String order) {

        Page<ItemLoanList> pageBean = null;

        return Message.success(pageBean);
    }

    @PostMapping("/detail")
    public Message<ItemLoan> detail(long itemLoanId) {

        ItemLoan loan = null;

        return Message.success(loan);
    }


}

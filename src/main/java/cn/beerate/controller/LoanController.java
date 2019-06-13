package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.Loan;
import cn.beerate.model.dto.LoanList;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user/loan")
public class LoanController extends UserBaseController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/add")
    public Message add(t_item_loan loan) {
        Message<t_item_loan> message = loanService.addItemByUser(loan, getUserId());
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("添加成功");
    }

    @GetMapping("/list")
    public Message<Page<LoanList>> list(int page, int size, String column, String order) {

        Page<LoanList> pageBean = null;

        return Message.success(pageBean);
    }

    @PostMapping("/detail")
    public Message<Loan> detail(long loanId) {

        Loan loan = null;

        return Message.success(loan);
    }


}

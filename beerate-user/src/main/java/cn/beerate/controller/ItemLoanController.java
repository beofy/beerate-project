package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.ItemLoanList;
import cn.beerate.model.dto.ItemLoan;
import cn.beerate.model.entity.Qt_item_loan;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.ItemLoanService;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
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

        Qt_item_loan itemLoan = Qt_item_loan.t_item_loan;
        Expression<ItemLoanList> expression = Projections.constructor(
                ItemLoanList.class,
                itemLoan.id,
                itemLoan.itemName,
                itemLoan.industryRealm,
                itemLoan.amount,
                itemLoan.period,
                itemLoan.auditStatus
        );

        Page<ItemLoanList> pageBean = itemLoanService.page(page, size, column, order, expression, itemLoan.id.eq(getUserId()));

        return Message.success(pageBean);
    }

    @PostMapping("/detail")
    public Message<ItemLoan> detail(long itemLoanId) {
        Qt_item_loan itemLoan = Qt_item_loan.t_item_loan;
        Expression<ItemLoan> expression = Projections.constructor(
                ItemLoan.class,
                itemLoan.id,
                itemLoan.companyName,
                itemLoan.logoUri,
                itemLoan.industryRealm,
                itemLoan.companyWebsite,
                itemLoan.companyIosUrl,
                itemLoan.companyAndroidUrl,
                itemLoan.isQuoted,
                itemLoan.stockCode,
                itemLoan.amount,
                itemLoan.amountUnit,
                itemLoan.purpose,
                itemLoan.period,
                itemLoan.periodUnit,
                itemLoan.repayment,
                itemLoan.businessProposalUri,
                itemLoan.businessLicenseUri,
                itemLoan.financialReportUri,
                itemLoan.auditReportUri,
                itemLoan.indebtednessUri,
                itemLoan.capitalFlowUri,
                itemLoan.endTime,
                itemLoan.isUrgent,
                itemLoan.isPlatformAuthentication,
                itemLoan.isFirstHandle,
                itemLoan.auditStatus
                );

        Predicate predicate = itemLoan.user.id.eq(getUserId()).and(itemLoan.id.eq(itemLoanId));

        ItemLoan loan = itemLoanService.getOne(expression, predicate);

        return Message.success(loan);
    }


}

package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.PreIpo;
import cn.beerate.model.dto.PreIpoList;
import cn.beerate.model.entity.Qt_item_pre_ipo;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.service.PreIpoService;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
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
        Qt_item_pre_ipo preIpo = Qt_item_pre_ipo.t_item_pre_ipo;

        Expression<PreIpoList> expression = Projections.constructor(
                PreIpoList.class,
                preIpo.id,
                preIpo.preIpoName,
                preIpo.industryRealm,
                preIpo.isNewThirdBoardListing,
                preIpo.iPOBaseDate,
                preIpo.auditStatus
        );

        Page<PreIpoList> pageBean = preIpoService.page(page, size, column, order, expression, preIpo.user.id.eq(getUserId()));

        return Message.success(pageBean);
    }

    @PostMapping("/detail")
    public Message<PreIpo> detail(long preIpoId) {
        Qt_item_pre_ipo preIpo = Qt_item_pre_ipo.t_item_pre_ipo;

        Expression<PreIpo> expression = Projections.constructor(
                PreIpo.class,
                preIpo.id,
                preIpo.preIpoName,
                preIpo.isNewThirdBoardListing,
                /*  ====================↓↓已挂牌↓↓==================== */
                preIpo.stockCode,
                preIpo.isPrincipalUnderwriter,
                preIpo.isTutoringBrokerage,
                preIpo.TutoringBrokerageName,
                /*  ====================↑↑已挂牌↑↑====================*/
                /*  ====================↓↓未挂牌↓↓==================== */
                preIpo.bidName,
                preIpo.companyNameIsPublic,
                preIpo.companyName,
                preIpo.city,
                /*  ====================↑↑未挂牌↑↑==================== */
                preIpo.industryRealm,
                preIpo.iPOBaseDate,
                preIpo.ratchetTerms,
                preIpo.ratchetTermsDescription,
                /*  ====================↓↓已挂牌↓↓==================== */
                preIpo.isPriceNegotiable,
                preIpo.intentionalPrice,
                preIpo.exchangeShares,
                preIpo.sharesAmount,
                preIpo.thresholdAmount,
                /* ====================↑↑已挂牌↑↑==================== */
                /* ====================↓↓未挂牌↓↓==================== */
                preIpo.currency,
                preIpo.loanAmount,
                preIpo.IntentionValuation,
                preIpo.lastYearProfits,
                preIpo.loanPeriod,
                preIpo.purpose,
                preIpo.investLightSpot,
                /*  ====================↑↑未挂牌↑↑==================== */
                preIpo.businessProposalUri,
                preIpo.contact,
                preIpo.contactMobile,
                preIpo.contentDescription,
                preIpo.endTime,
                preIpo.isUrgent,
                preIpo.isPlatformAuthentication,
                preIpo.isFirstHandle,
                preIpo.auditStatus
        );

        Predicate predicate = preIpo.user.id.eq(getUserId()).and(preIpo.id.eq(preIpoId));

        PreIpo pre =preIpoService.getOne(expression,predicate);

        return Message.success(pre);
    }
}

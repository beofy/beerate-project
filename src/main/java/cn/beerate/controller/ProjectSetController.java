package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.model.dto.*;
import cn.beerate.model.entity.t_user_attention;
import cn.beerate.service.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目集
 */
@RestController
@RequestMapping("/user/projectSet")
public class ProjectSetController extends UserBaseController {

    private LoanService loanService;
    private BlockTradeService blockTradeService;
    private PreIpoService preIpoService;
    private StockPledgeService stockPledgeService;
    private StockTransferService stockTransferService;
    private UserVisitorService userVisitorService;
    private UserAttentionService userAttentionService;
    private UserConcatService userConcatService;
    private UserBusinessService userBusinessService;
    private UserItemAcceptService userItemAcceptService;

    public ProjectSetController(LoanService loanService, BlockTradeService blockTradeService, PreIpoService preIpoService, StockPledgeService stockPledgeService, StockTransferService stockTransferService, UserVisitorService userVisitorService, UserAttentionService userAttentionService, UserConcatService userConcatService, UserBusinessService userBusinessService, UserItemAcceptService userItemAcceptService) {
        this.loanService = loanService;
        this.blockTradeService = blockTradeService;
        this.preIpoService = preIpoService;
        this.stockPledgeService = stockPledgeService;
        this.stockTransferService = stockTransferService;
        this.userVisitorService = userVisitorService;
        this.userAttentionService = userAttentionService;
        this.userConcatService = userConcatService;
        this.userBusinessService = userBusinessService;
        this.userItemAcceptService = userItemAcceptService;
    }

    /**
     * 项目融资
     */
    @PostMapping("/loan")
    public Page<Loan> loan(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return loanService.pageLoan(page, size, column, order);
    }

    /**
     * 大宗交易
     */
    @PostMapping("/blockTrade")
    public Page<BlockTrade> blockTrade(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return blockTradeService.pageBlockTrade(page, size, column, order);
    }

    /**
     * preIpo
     */
    @PostMapping("/preIpo")
    public Page<PreIpo> preIpo(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return preIpoService.pagePreIpo(page, size, column, order);
    }

    /**
     * 股票质押
     */
    @PostMapping("/stockPledge")
    public Page<StockPledge> stockPledge(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return stockPledgeService.pageStockPledge(page, size, column, order);
    }

    /**
     * 老股转让
     */
    @PostMapping("/stockTransfer")
    public Page<StockTransfer> stockTransfer(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return stockTransferService.pageStockTransfer(page, size, column, order);
    }

    /**
     * 项目方列表(已认证)
     */
    @PostMapping("/list")
    public Message<Page<Projector>> list(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order) {
        return Message.success(userBusinessService.pageOfProjector(page, size, column, order));
    }

    /**
     * 项目方简介
     */
    @PostMapping("/intro")
    public Message<ProjectorIntro> intro(long userId) {
        //添加访客
        userVisitorService.addUserVisitor(getUser()==null?0L:getUserId(), userId, getIp());

        return userBusinessService.projectorIntro(userId);
    }

    /**
     * 项目方详细信息
     */
    @PostMapping("/detail")
    public Message<ProjectorDetail> detail(long userId) {
        //添加联系方
        userConcatService.addUserContact(getUser()==null?0L:getUserId(), userId);

        return userBusinessService.projectorDetail(userId);
    }

    /**
     * 添加关注
     */
    @PostMapping("/addAttention")
    public Message<String> addAttention(long attentionUserId) {
        Message<t_user_attention> message = userAttentionService.addUserAttention(getUserId(), attentionUserId);
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("关注成功");
    }

    /**
     * 项目是否关注
     */
    @PostMapping("/isAttention")
    public Message<Boolean> isAttention(long attentionUserId) {
        t_user_attention userAttention = userAttentionService.isAttention(getUserId(), attentionUserId);
        if (userAttention == null) {
            return Message.success(false);
        }

        return Message.success(true);
    }

    /**
     * 取消关注
     */
    @PostMapping("/delAttention")
    public Message<String> delAttention(long attentionUserId) {
        Message<t_user_attention> message = userAttentionService.delAttention(getUserId(), attentionUserId);
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return Message.ok("取消关注成功");
    }

    /**
     * 项目方ta的关注
     */
    @PostMapping("/attention")
    public Page<Projector> attention(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order, long userId) {
        return userAttentionService.attention(page, size, "business.userId", "desc", userId);
    }

    /**
     * 关注ta的项目方
     */
    @PostMapping("/beAttention")
    public Page<Projector> beAttention(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order, long attentionUserId) {
        return userAttentionService.beAttention(page, size, "business.userId", "desc", attentionUserId);
    }

    /**
     * 项目方ta的联系
     */
    @PostMapping("/contact")
    public Page<Projector> contact(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order, long userId) {
        return userConcatService.contact(page, size, "business.userId", "desc", userId);
    }

    /**
     * 联系ta的项目方
     */
    @PostMapping("/beContact")
    public Page<Projector> beContact(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order, long contactUserId) {
        return userConcatService.beContact(page, size, "business.userId", "desc", contactUserId);
    }

    /**
     * 项目方ta收到的项目
     */
    @PostMapping("/acceptItem")
    public Page<UserItemAccept> acceptItem(int page, int size, @RequestParam(required = false) String column, @RequestParam(required = false) String order, long userId) {
        return userItemAcceptService.userItemAccept(page, size, column, order, userId);
    }
}

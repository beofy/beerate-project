package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.PreIpoDao;
import cn.beerate.model.*;
import cn.beerate.model.dto.MyPreIpo;
import cn.beerate.model.dto.PreIpo;
import cn.beerate.model.dto.PreIpoDetail;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.model.entity.t_user_item_delivery;
import cn.beerate.service.PreIpoService;
import cn.beerate.service.UserItemDeliveryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class PreIpoServiceImpl extends ItemCommonServiceImpl<t_item_pre_ipo> implements PreIpoService {

    private PreIpoDao preIpoDao;
    private UserItemDeliveryService userItemDeliveryService;
    public PreIpoServiceImpl(PreIpoDao preIpoDao,UserItemDeliveryService userItemDeliveryService) {
        super(preIpoDao);
        this.preIpoDao = preIpoDao;
        this.userItemDeliveryService=userItemDeliveryService;
    }

    private Message<String> preIpoValid(t_item_pre_ipo preIpo){

        if(StringUtils.isBlank(preIpo.getPreIpoName())){
            return Message.error("请填写项目名称");
        }

        if(preIpo.getIndustryRealm()== IndustryRealm.NONE){
            return Message.error("请选择标的所处行业领域");
        }

        if(preIpo.getIPOBaseDate().before(new Date())){
            return Message.error("请选择拟IPO基准日");
        }

        if(preIpo.getRatchetTerms()== RatchetTerms.NONE){
            return Message.error("请选择对赌条件");
        }

        if(StringUtils.isBlank(preIpo.getRatchetTermsDescription())){
            return Message.error("请填写对赌描述");
        }

        if(preIpo.getIsNewThirdBoardListing()==null){
            return Message.error("请选择是否挂牌新三板");
        }

        if(preIpo.getIsNewThirdBoardListing()){//已挂牌

            if (StringUtils.isBlank(preIpo.getStockCode())){
                return Message.error("请填写股票代码");
            }

            if(preIpo.getIsPrincipalUnderwriter()==null){
                return Message.error("请选择是否主承销券商");
            }

            if(preIpo.getIsTutoringBrokerage()==null){
                return Message.error("请选择辅导券商是否已进场");
            }

            if(StringUtils.isBlank(preIpo.getTutoringBrokerageName())){
                return Message.error("请填写辅导券商名称");
            }

            if (preIpo.getIsPriceNegotiable()==null){
                return Message.error("请选择价格是否面议");
            }

            if (preIpo.getIntentionalPrice()==null||preIpo.getIntentionalPrice()<0){
                return Message.error("请填写意向价格");
            }

            if(preIpo.getExchangeShares()==null||preIpo.getExchangeShares()<0){
                return Message.error("请填写交易股数");
            }

            if (preIpo.getSharesAmount()==null||preIpo.getSharesAmount()<0){
                return Message.error("请填写份额规模");
            }

            if (preIpo.getThresholdAmount()==null||preIpo.getThresholdAmount()<0){
                return Message.error("请填写参与门槛");
            }

        }

        if (!preIpo.getIsNewThirdBoardListing()){//未挂牌

            if (StringUtils.isBlank(preIpo.getBidName())){
                return Message.error("请填写标的名称");
            }

            if (preIpo.getCompanyNameIsPublic()==null){
                return Message.error("请选择标的企业名称是否公开");
            }

            if(StringUtils.isBlank(preIpo.getCompanyName())){
                return Message.error("请填写标的企业名称");
            }

            if (StringUtils.isBlank(preIpo.getCity())){
                return Message.error("请填写所在省市");
            }

            if (preIpo.getCurrency()== Currency.NONE){
                return Message.error("请选择币种");
            }

            if(preIpo.getLoanAmount()==null||preIpo.getLoanAmount()<0){
                return Message.error("请填写融资金额");
            }

            if (preIpo.getIntentionValuation()==null||preIpo.getIntentionValuation()<0){
                return Message.error("请填写估值意向");
            }

            if(preIpo.getLastYearProfits()==null||preIpo.getLastYearProfits()<0){
                return Message.error("请填写估值意向");
            }

            if (preIpo.getLoanPeriod()==LoanPeriod.NONE){
                return Message.error("请选择融资期限");
            }

            if (StringUtils.isBlank(preIpo.getPurpose())){
                return Message.error("请填写融资用途");
            }

            if (StringUtils.isBlank(preIpo.getInvestLightSpot())){
                return Message.error("请填写投资亮点");
            }
        }

        if (StringUtils.isBlank(preIpo.getBusinessProposalUri())){
            return Message.error("BP计划书不存在");
        }

        if (StringUtils.isBlank(preIpo.getContact())){
            return Message.error("请填写联系人");
        }

        if (StringUtils.isBlank(preIpo.getContactMobile())){
            return Message.error("请填写联系人电话");
        }

        if (StringUtils.isBlank(preIpo.getContentDescription())){
            return Message.error("请填写内容描述");
        }

        return Message.ok("检验通过");
    }

    @Override
    @Transactional
    public Message<t_item_pre_ipo> addItem(t_item_pre_ipo preIpo) {
        Message<String> message =  preIpoValid(preIpo);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return super.addItem(preIpo);
    }

    @Override
    public Message<t_item_pre_ipo> addItemByUser(t_item_pre_ipo preIpo, long userId) {
        Message<t_item_pre_ipo> message1 = super.addItemByUser(preIpo,userId);
        if (message1.fail()){
            return message1;
        }

        //添加投递项目列表
        t_item_pre_ipo pre_ipo = message1.getData();
        Message<t_user_item_delivery> message2 = userItemDeliveryService.addUserItemDelivery(pre_ipo.getUser().getId(),pre_ipo.getId(),pre_ipo.getPreIpoName(), ItemType.PRE_IPO);
        if (message2.fail()){
            return Message.error(message2.getMsg());
        }

        return message1;
    }

    @Override
    public Page<MyPreIpo> pageMyPreIpoUser(int page, int size, String column, String order, long userId) {
        return preIpoDao.pageMyPreIpoByUser(getPageable(page, size, column, order),userId);
    }

    @Override
    public PreIpoDetail preIpoDetailByUser(long preIpoId, long userId) {
        return preIpoDao.preIpoDetailByUser(preIpoId,userId);
    }

    @Override
    public Message<t_item_pre_ipo> updateItemByUser(t_item_pre_ipo preIpo, long itemId, long userId) {
        //参数校验
        Message message = preIpoValid(preIpo);
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        t_item_pre_ipo pre_ipo = preIpoDao.findByIdAndUserId(itemId, userId);
        if (pre_ipo.getAuditStatus() != AuditStatus.SUPPLEMENT) {
            return Message.error("非补充资料状态");
        }

        //更改信息
        pre_ipo.setPreIpoName(preIpo.getPreIpoName());
        pre_ipo.setIsNewThirdBoardListing(preIpo.getIsNewThirdBoardListing());
        pre_ipo.setStockCode(preIpo.getStockCode());
        pre_ipo.setIsPrincipalUnderwriter(preIpo.getIsPrincipalUnderwriter());
        pre_ipo.setIsTutoringBrokerage(preIpo.getIsTutoringBrokerage());
        pre_ipo.setTutoringBrokerageName(preIpo.getTutoringBrokerageName());
        pre_ipo.setBidName(preIpo.getBidName());
        pre_ipo.setCompanyNameIsPublic(preIpo.getCompanyNameIsPublic());
        pre_ipo.setCompanyName(preIpo.getCompanyName());
        pre_ipo.setCity(preIpo.getCity());
        pre_ipo.setIndustryRealm(preIpo.getIndustryRealm());
        pre_ipo.setIPOBaseDate(preIpo.getIPOBaseDate());
        pre_ipo.setRatchetTerms(preIpo.getRatchetTerms());
        pre_ipo.setRatchetTermsDescription(preIpo.getRatchetTermsDescription());
        pre_ipo.setIsPriceNegotiable(preIpo.getIsPriceNegotiable());
        pre_ipo.setIntentionalPrice(preIpo.getIntentionalPrice());
        pre_ipo.setExchangeShares(preIpo.getExchangeShares());
        pre_ipo.setSharesAmount(preIpo.getSharesAmount());
        pre_ipo.setThresholdAmount(preIpo.getThresholdAmount());
        pre_ipo.setCurrency(preIpo.getCurrency());
        pre_ipo.setLoanAmount(preIpo.getLoanAmount());
        pre_ipo.setIntentionValuation(preIpo.getIntentionValuation());
        pre_ipo.setLastYearProfits(preIpo.getLastYearProfits());
        pre_ipo.setLoanPeriod(preIpo.getLoanPeriod());
        pre_ipo.setPurpose(preIpo.getPurpose());
        pre_ipo.setInvestLightSpot(preIpo.getInvestLightSpot());
        pre_ipo.setBusinessProposalUri(preIpo.getBusinessProposalUri());
        pre_ipo.setContact(preIpo.getContact());
        pre_ipo.setContactMobile(preIpo.getContactMobile());
        pre_ipo.setContentDescription(preIpo.getContentDescription());

        return super.updateItem(pre_ipo);
    }

    @Override
    public Page<PreIpo> pagePreIpo(int page, int size, String column, String order) {
        return preIpoDao.pagePreIpo(getPageable(page, size, column, order));
    }
}

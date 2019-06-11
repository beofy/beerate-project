package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.PreIpoDao;
import cn.beerate.model.*;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.PreIpoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class PreIpoServiceImpl extends ItemCommonServiceImpl<t_item_pre_ipo> implements PreIpoService {

    private PreIpoDao preIpoDao;

    public PreIpoServiceImpl(PreIpoDao preIpoDao) {
        super(preIpoDao);
        this.preIpoDao = preIpoDao;
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

        return super.itemModelValid(preIpo);
    }

    @Transactional
    public Message<t_item_pre_ipo> addPreIpo( t_item_pre_ipo preIpo){
        Message<String> message =  preIpoValid(preIpo);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.success(preIpoDao.save(preIpo));
    }

    @Transactional
    public Message<t_item_pre_ipo> addPreIpoByUser( t_item_pre_ipo preIpo,long userId){
        t_user user = new t_user();
        user.setId(userId);

        preIpo.setUser(user);
        preIpo.setAuditStatus(AuditStatus.WAIT_AUDIT);

        return addPreIpo(preIpo);
    }

    @Transactional
    public Message<t_item_pre_ipo> addPreIpoByAdmin( t_item_pre_ipo preIpo,long adminId){
        t_admin admin = new t_admin();
        admin.setId(adminId);

        preIpo.setAdmin(admin);
        preIpo.setAuditStatus(AuditStatus.PASS_AUDIT);

        return addPreIpo(preIpo);
    }
}

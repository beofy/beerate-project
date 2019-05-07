package cn.beerate.model;

import cn.beerate.common.Message;
import cn.beerate.model.entity.*;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class ModelValidate {

    private static Message<String> itemModelValid(ItemModel itemModel) {
        if (itemModel.getEndTime().before(new Date())) {
            return Message.error("请选择正确的项目结束日期");
        }

        if (itemModel.getIsUrgent() == null) {
            return Message.error("请选择是否加急");
        }

        if (itemModel.getIsPlatformAuthentication() == null) {
            return Message.error("请选择是否需要平台认证");
        }

        if (itemModel.getIsFirstHandle() == null) {
            return Message.error("请选择是否一手");
        }

        if (itemModel.getIsShow() == null) {
            return Message.error("请选择是否前台展示");
        }

        if (itemModel.getAuditStatus() == null) {
            return Message.error("项目审核状态不正确");
        }

        return Message.ok("校验成功");
    }

    public static Message<String> blockTradeValid(t_item_block_trade blockTrade) {

        if (StringUtils.isBlank(blockTrade.getBlockTradeName())) {
            return Message.error("请输入项目名称");
        }

        if (StringUtils.isBlank(blockTrade.getStockCode()) || blockTrade.getStockCode().length() != 6) {
            return Message.error("请输入正确股票代码");
        }

        if (blockTrade.getExchangeRate() == null || blockTrade.getExchangeRate() < 0 || blockTrade.getExchangeRate() > 100) {
            return Message.error("请输入正确的交易折价");
        }

        if (blockTrade.getUnderweightShares() == null || blockTrade.getUnderweightShares() < 0) {
            return Message.error("请输入正确的减持股数");
        }

        if (blockTrade.getUnderweightAmount() == null || blockTrade.getUnderweightAmount() < 0) {
            return Message.error("请输入正确的减持金额");
        }

        if (blockTrade.getUnderWeightIdentification() == null || blockTrade.getUnderWeightIdentification() == UnderWeightIdentification.NONE) {
            return Message.error("请输入选择减持方身份");
        }


        if (blockTrade.getIsConfidence() == null) {
            return Message.error("请选择是否增信");
        }

        if (BooleanUtils.isTrue(blockTrade.getIsConfidence())) {//增信

            if (blockTrade.getExpectedReturn() == null || blockTrade.getExpectedReturn() < 0) {
                return Message.error("请输入正确的预期收益率");
            }

            if (blockTrade.getConfidencePeriod().before(new Date())) {
                return Message.error("请输入正确的增信期限");
            }

            if (blockTrade.getCreditIdentification() == null || CreditIdentification.NONE == blockTrade.getCreditIdentification()) {
                return Message.error("请选择正确的增信身份");
            }

            if (blockTrade.getConfidenceShare() == null || blockTrade.getConfidenceShare() < 0) {
                return Message.error("请输入正确的增信方超额收益分成");
            }

            if (blockTrade.getConfidenceIsPublic() == null) {
                return Message.error("请选择增信是否公开");
            }
        }

        return itemModelValid(blockTrade);
    }

    public static Message<String> itemLoanValid(t_item_loan itemLoan){

        if(StringUtils.isBlank(itemLoan.getItemName())){
            return Message.error("请输入正确的项目名称");
        }

        if(StringUtils.isBlank(itemLoan.getCompanyName())){
            return Message.error("请输入公司名称");
        }

        if(StringUtils.isBlank(itemLoan.getLogoUri())){
            return Message.error("公司logo不存在");
        }

        if(itemLoan.getIndustryRealm() == null || itemLoan.getIndustryRealm() ==IndustryRealm.NONE){
            return Message.error("请选择正确的行业领域");
        }

        if(StringUtils.isBlank(itemLoan.getCompanyWebsite())){
            return Message.error("请输入公司官网");
        }

        if(StringUtils.isBlank(itemLoan.getCompanyIosUrl())){
            return Message.error("请输入ios应用地址");
        }

        if(StringUtils.isBlank(itemLoan.getCompanyAndroidUrl())){
            return Message.error("请输入android应用地址");
        }

        if(itemLoan.getIsQuoted()==null){
            return Message.error("请选择是否上市");
        }

        if(BooleanUtils.isTrue(itemLoan.getIsQuoted())){
            if(StringUtils.isBlank(itemLoan.getStockCode())){
                return Message.error("请输入股票代码");
            }
        }

        if(itemLoan.getAmount()==null||itemLoan.getAmount()<0){
            return Message.error("请输入正确的融资金额");
        }

        if(itemLoan.getAmountUnit()==null){
            return Message.error("请选择金额单位");
        }

        if(StringUtils.isBlank(itemLoan.getPurpose())){
            return Message.error("请输入资金用途");
        }

        if(itemLoan.getPeriod()==null||itemLoan.getPeriod()<0){
            return Message.error("请输入借款期限");
        }

        if(itemLoan.getPeriodUnit()==null){
            return Message.error("请选择期限单位");
        }

        if(StringUtils.isBlank(itemLoan.getRepayment())){
            return Message.error("请填写还款来源");
        }

        if(StringUtils.isBlank(itemLoan.getBusinessProposalUri())){
            return Message.error("BP计划书不存在");
        }

        if(StringUtils.isBlank(itemLoan.getBusinessLicenseUri())){
            return Message.error("营业执照不存在");
        }

        if(StringUtils.isBlank(itemLoan.getFinancialReportUri())){
            return Message.error("财务报表不存在");
        }

        if(StringUtils.isBlank(itemLoan.getAuditReportUri())){
            return Message.error("审计报告不存在");
        }

        if(StringUtils.isBlank(itemLoan.getIndebtednessUri())){
            return Message.error("负载明细不存在");
        }

        if(StringUtils.isBlank(itemLoan.getCapitalFlowUri())){
            return Message.error("银行流水不存在");
        }

        return itemModelValid(itemLoan);
    }

    public static Message<String> overseasListingValid(t_item_overseas_listing overseasListing){

        if(StringUtils.isBlank(overseasListing.getRealName())){
            return Message.error("请填写真实姓名");
        }

        if(StringUtils.isBlank(overseasListing.getMobile())){
            return Message.error("请填写手机号码");
        }

        if(StringUtils.isBlank(overseasListing.getJob())){
            return Message.error("请填写职位");
        }

        if(StringUtils.isBlank(overseasListing.getCompanyName())){
            return Message.error("请填写企业名称");
        }

        if(StringUtils.isBlank(overseasListing.getCompanyBusiness())){
            return Message.error("请填写企业主营业务");
        }

        if(overseasListing.getNetProfit()==null){
            return Message.error("请选择净利润情况");
        }

        return Message.ok("校验成功");
    }

    public static Message<String> preIpoValid(t_item_pre_ipo preIpo){

        if(StringUtils.isBlank(preIpo.getPreIpoName())){
            return Message.error("请填写项目名称");
        }

        if(preIpo.getIndustryRealm()==null||preIpo.getIndustryRealm()==IndustryRealm.NONE){
            return Message.error("请选择标的所处行业领域");
        }

        if(preIpo.getIPOBaseDate().before(new Date())){
            return Message.error("请选择拟IPO基准日");
        }

        if(preIpo.getRatchetTerms()==null||preIpo.getRatchetTerms()==RatchetTerms.NONE){
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

            if (preIpo.getCurrency()==null){
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

            if (preIpo.getLoanPeriod()==null){
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

        return itemModelValid(preIpo);
    }

    public static Message<String> stockPledgeValid(t_item_stock_pledge stockPledge){

        if (StringUtils.isBlank(stockPledge.getFinancingBody())){
            return Message.error("请填写融资主体");
        }

        if (StringUtils.isBlank(stockPledge.getStockCode())){
            return Message.error("请填写股票代码");
        }


        if(stockPledge.getStockNature()==null){
            return Message.error("请选择质押股票流通性质");
        }

        if (stockPledge.getStockNature()!=StockNature.CIRCULATE){
            if (stockPledge.getSalesDeadline().before(new Date())){
                return Message.error("请选择限售到期日");
            }
        }

        if (stockPledge.getStockBlock()==null){
            return Message.error("请选择质押股票所属板块");
        }

        if (stockPledge.getIsQuoteOrActualController()==null){
            return Message.error("请选择融资人是否为上市公司控股或实际控制人");
        }

        if (stockPledge.getHoldStockShares()==null||stockPledge.getHoldStockShares()<0){
            return Message.error("请填写总持有股票数");
        }

        if (stockPledge.getPledgeStockShares()==null||stockPledge.getPledgeStockShares()<0){
            return Message.error("请填写质押股票数");
        }

        if (stockPledge.getSurplusPledgeStockShares()==null||stockPledge.getSurplusPledgeStockShares()<0){
            return Message.error("请填写剩余可质押股票数");
        }

        if (stockPledge.getLoanAmount()==null||stockPledge.getLoanAmount()<0){
            return Message.error("请填写融资金额");
        }

        if (stockPledge.getPledgeRates()==null||stockPledge.getPledgeRates()<0){
            return Message.error("请填写质押率");
        }

        if (stockPledge.getLoanPeriod()==null){
            return Message.error("请选择融资周期");
        }

        if (stockPledge.getFinancingPartyPaysCostRates()==null||stockPledge.getFinancingPartyPaysCostRates()<0){
            return Message.error("请填写融资方愿意支付的融资成本");
        }

        if (StringUtils.isBlank(stockPledge.getRecentOneYearProfitsDescription())){
            return Message.error("请填写最近一个年度盈利情况");
        }

        if (StringUtils.isBlank(stockPledge.getPurpose())){
            return Message.error("请填写借款用途说明");
        }

        if (StringUtils.isBlank(stockPledge.getRepaymentDescription())){
            return Message.error("请填写还款来源说明");
        }

        if (StringUtils.isBlank(stockPledge.getEnhancementConfidenceMeasures())){
            return Message.error("请填写其他增信措施");
        }

        return itemModelValid(stockPledge);
    }

    public static Message<String> stockTransferValid(t_item_stock_transfer stockTransfer){

        if (StringUtils.isBlank(stockTransfer.getBidName())){
            return Message.error("请填写标的名称");
        }

        if (stockTransfer.getIsQuoted()==null){
            return Message.error("请选择标的所属阶段");
        }

        if (stockTransfer.getCompanyNameIsPublic()==null){
            return Message.error("请选择企业名称是否公开");
        }

        if (StringUtils.isBlank(stockTransfer.getCompanyName())){
            return Message.error("请填写企业名称");
        }

        if (stockTransfer.getIndustryRealm()==null||stockTransfer.getIndustryRealm()==IndustryRealm.NONE){
            return Message.error("请选择行业领域");
        }

        if (stockTransfer.getCurrency()==null){
            return Message.error("请选择币种");
        }

        if (stockTransfer.getLastYearProfits()==null||stockTransfer.getLastYearProfits()<0){
            return Message.error("请填写去年净利润");
        }

        if (stockTransfer.getCurrentValuation()==null||stockTransfer.getCurrentValuation()<0){
            return Message.error("请填写本轮估值");
        }

        if (stockTransfer.getTransferAmount()==null||stockTransfer.getTransferAmount()<0){
            return Message.error("请填写转让金额");
        }

        if (stockTransfer.getIsPrivacyEquityRatio()==null){
            return Message.error("请选择转让股权比例是否保密");
        }

        if (stockTransfer.getEquityRatio()==null||stockTransfer.getEquityRatio()<0){
            return Message.error("请填写转让股权比例");
        }

        if (StringUtils.isBlank(stockTransfer.getInvestLightSpot())){
            return Message.error("请填写投资亮点");
        }

        if (StringUtils.isBlank(stockTransfer.getContact())){
            return Message.error("请填写联系人");
        }

        if (StringUtils.isBlank(stockTransfer.getContactMobile())){
            return Message.error("请填写联系人电话");
        }

        if (StringUtils.isBlank(stockTransfer.getContentDescription())){
            return Message.error("请填写内容描述");
        }

        return itemModelValid(stockTransfer);
    }

}

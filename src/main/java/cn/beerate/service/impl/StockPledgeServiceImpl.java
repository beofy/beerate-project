package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.StockPledgeDao;
import cn.beerate.model.ItemType;
import cn.beerate.model.StockBlock;
import cn.beerate.model.StockNature;
import cn.beerate.model.entity.t_item_stock_pledge;
import cn.beerate.model.entity.t_user_item_delivery;
import cn.beerate.service.StockPledgeService;
import cn.beerate.service.UserItemDeliveryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class StockPledgeServiceImpl extends ItemCommonServiceImpl<t_item_stock_pledge> implements StockPledgeService {

    private StockPledgeDao stockPledgeDao;
    private UserItemDeliveryService userItemDeliveryService;
    public StockPledgeServiceImpl(StockPledgeDao stockPledgeDao,UserItemDeliveryService userItemDeliveryService) {
        super(stockPledgeDao);
        this.stockPledgeDao = stockPledgeDao;
        this.userItemDeliveryService=userItemDeliveryService;
    }

    private Message<String> stockPledgeValid(t_item_stock_pledge stockPledge){

        if (StringUtils.isBlank(stockPledge.getFinancingBody())){
            return Message.error("请填写融资主体");
        }

        if (StringUtils.isBlank(stockPledge.getStockCode())){
            return Message.error("请填写股票代码");
        }

        if(stockPledge.getStockNature()== StockNature.NONE){
            return Message.error("请选择质押股票流通性质");
        }

        if (stockPledge.getStockNature()!=StockNature.CIRCULATE){
            if (stockPledge.getSalesDeadline().before(new Date())){
                return Message.error("请选择限售到期日");
            }
        }

        if (stockPledge.getStockBlock()== StockBlock.NONE){
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

        return Message.ok("检验通过");
    }

    @Override
    @Transactional
    public Message<t_item_stock_pledge> addItem(t_item_stock_pledge stockPledge) {
        Message<String> message = stockPledgeValid(stockPledge);
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        Message<t_item_stock_pledge> message1 = super.addItem(stockPledge);
        if (message1.fail()){
            return message1;
        }

        //添加投递项目列表
        t_item_stock_pledge stock_pledge = message1.getData();
        Message<t_user_item_delivery> message2 = userItemDeliveryService.addUserItemDelivery(stock_pledge.getUser().getId(),stock_pledge.getId(),stock_pledge.getFinancingBody(), ItemType.STOCK_PLEDGE);
        if (message2.fail()){
            return Message.error(message2.getMsg());
        }

        return message1;
    }
}

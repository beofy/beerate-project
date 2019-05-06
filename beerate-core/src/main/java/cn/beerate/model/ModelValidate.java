package cn.beerate.model;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_block_trade;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class ModelValidate {

    private static Message<String> itemModelValid(ItemModel itemModel){
        if(itemModel.getEndTime().before(new Date())){
            return Message.error("请选择正确的项目结束日期");
        }

        if(itemModel.getIsUrgent()==null){
            return Message.error("请选择是否加急");
        }

        if(itemModel.getIsPlatformAuthentication()==null){
            return Message.error("请选择是否需要平台认证");
        }

        if(itemModel.getIsFirstHandle()==null){
            return Message.error("请选择是否一手");
        }

        if(itemModel.getIsShow()==null){
            return Message.error("请选择是否前台展示");
        }

        if(itemModel.getAuditStatus()==null){
            return Message.error("项目审核状态不正确");
        }

        return Message.ok("校验成功");
    }

    public static Message<String> blockTradeValid(t_item_block_trade blockTrade){
        Message<String> message = itemModelValid(blockTrade);
        if(message.fail()){
            return message;
        }

        if(StringUtils.isBlank(blockTrade.getBlockTradeName())){
            return Message.error("请输入项目名称");
        }

        if(StringUtils.isBlank(blockTrade.getStockCode())||blockTrade.getStockCode().length()!=6){
            return Message.error("请输入正确股票代码");
        }

        if(blockTrade.getExchangeRate()==null||blockTrade.getExchangeRate()<0||blockTrade.getExchangeRate()>100){
            return Message.error("请输入正确的交易折价");
        }

        if(blockTrade.getUnderweightShares()==null||blockTrade.getUnderweightShares()<0){
            return Message.error("请输入正确的减持股数");
        }

        if(blockTrade.getUnderweightAmount()==null||blockTrade.getUnderweightAmount()<0){
            return Message.error("请输入正确的减持金额");
        }

        UnderWeightIdentification underWeightIdentification = EnumUtils.getEnumIgnoreCase(UnderWeightIdentification.class,blockTrade.getUnderweightIdentification());
        if(underWeightIdentification==null||underWeightIdentification==UnderWeightIdentification.NONE){
            return Message.error("请输入选择减持方身份");
        }


        if(blockTrade.getIsConfidence()==null){
            return Message.error("请选择是否增信");
        }

        if(BooleanUtils.isTrue(blockTrade.getIsConfidence())){//增信

            if(blockTrade.getExpectedReturn()==null||blockTrade.getExpectedReturn()<0){
                return Message.error("请输入正确的预期收益率");
            }

            if(blockTrade.getConfidencePeriod().before(new Date())){
                return Message.error("请输入正确的增信期限");
            }

            CreditIdentification creditIdentification =EnumUtils.getEnumIgnoreCase(CreditIdentification.class,blockTrade.getCreditIdentification());
            if(creditIdentification==null||CreditIdentification.NONE==creditIdentification){
                return Message.error("请选择正确的增信身份");
            }

            if(blockTrade.getConfidenceShare()==null||blockTrade.getConfidenceShare()<0){
                return Message.error("请输入正确的增信方超额收益分成");
            }

            if(blockTrade.getConfidenceIsPublic()==null){
                return Message.error("请选择增信是否公开");
            }
        }

        if(blockTrade.getPositionLowPrice()==null||blockTrade.getPositionLowPrice()<0){
            return Message.error("请输入正确的低价持仓");
        }

        return Message.ok("校验成功");
    }
}

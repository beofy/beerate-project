package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.BlockTradeDao;
import cn.beerate.model.CreditIdentification;
import cn.beerate.model.ItemType;
import cn.beerate.model.UnderWeightIdentification;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.model.entity.t_user_item_delivery;
import cn.beerate.service.BlockTradeService;
import cn.beerate.service.UserItemDeliveryService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class BlockTradeServiceImpl extends ItemCommonServiceImpl<t_item_block_trade> implements BlockTradeService {
    private BlockTradeDao blockTradeDao;
    private UserItemDeliveryService userItemDeliveryService;
    public BlockTradeServiceImpl(BlockTradeDao blockTradeDao,UserItemDeliveryService userItemDeliveryService) {
        super(blockTradeDao);
        this.blockTradeDao = blockTradeDao;
        this.userItemDeliveryService=userItemDeliveryService;
    }

    private Message<String> blockTradeValid(t_item_block_trade blockTrade) {
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

        if (blockTrade.getUnderWeightIdentification() == UnderWeightIdentification.NONE) {
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

            if (CreditIdentification.NONE == blockTrade.getCreditIdentification()) {
                return Message.error("请选择正确的增信身份");
            }

            if (blockTrade.getConfidenceShare() == null || blockTrade.getConfidenceShare() < 0) {
                return Message.error("请输入正确的增信方超额收益分成");
            }

            if (blockTrade.getConfidenceIsPublic() == null) {
                return Message.error("请选择增信是否公开");
            }
        }
        return Message.ok("检验通过");
    }

    @Override
    @Transactional
    public Message<t_item_block_trade> addItem(t_item_block_trade blockTrade) {
        //参数校验
        Message<String> message = blockTradeValid(blockTrade);
        if(message.fail()){
            return Message.error(message.getMsg());
        }

        if (!BooleanUtils.isTrue(blockTrade.getIsConfidence())) {
            blockTrade.setExpectedReturn(0.00);
            blockTrade.setConfidencePeriod(null);
            blockTrade.setCreditIdentification(CreditIdentification.NONE);
            blockTrade.setConfidenceShare(0);
            blockTrade.setConfidenceIsPublic(false);
        }

        Message<t_item_block_trade> message1 = super.addItem(blockTrade);
        if (message1.fail()){
            return message1;
        }

        //添加投递项目列表
        t_item_block_trade block_trade = message1.getData();
        Message<t_user_item_delivery> message2 = userItemDeliveryService.addUserItemDelivery(block_trade.getUser().getId(),block_trade.getId(),block_trade.getBlockTradeName(), ItemType.BLOCK_TRADE);
        if (message2.fail()){
            return Message.error(message2.getMsg());
        }

        return message1;
    }
}

package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.BlockTradeDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.CreditIdentification;
import cn.beerate.model.UnderWeightIdentification;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.BlockTradeService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class BlockTradeServiceImpl extends ItemCommonServiceImpl<t_item_block_trade> implements BlockTradeService {
    private BlockTradeDao blockTradeDao;

    public BlockTradeServiceImpl(BlockTradeDao blockTradeDao) {
        super(blockTradeDao);
        this.blockTradeDao = blockTradeDao;
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
        return super.itemModelValid(blockTrade);
    }

    /**
     * 添加大宗交易
     */
    @Transactional
    public Message<t_item_block_trade> addBlockTrade(t_item_block_trade blockTrade){
        //参数校验
        Message<String> messageValid = blockTradeValid(blockTrade);
        if(messageValid.fail()){
            return Message.error(messageValid.getMsg());
        }

        if (!BooleanUtils.isTrue(blockTrade.getIsConfidence())) {
            blockTrade.setExpectedReturn(0.00);
            blockTrade.setConfidencePeriod(null);
            blockTrade.setCreditIdentification(CreditIdentification.NONE);
            blockTrade.setConfidenceShare(0);
            blockTrade.setConfidenceIsPublic(false);
        }


       return Message.success(blockTradeDao.save(blockTrade));

    }

    /**
     *  添加大宗交易-前台用户
     */
    @Transactional
    public Message<t_item_block_trade> addBlockTradeByUser(t_item_block_trade blockTrade,long userId){
        t_user user = new t_user();
        user.setId(userId);

        blockTrade.setUser(user);
        blockTrade.setAuditStatus(AuditStatus.WAIT_AUDIT);//设置审核状态-等待审核

        return addBlockTrade(blockTrade);
    }

    /**
     *  添加大宗交易-后台管理员
     */
    @Transactional
    public Message<t_item_block_trade> addBlockTradeByAdmin(t_item_block_trade blockTrade,long adminId){
        t_admin admin = new t_admin();
        admin.setId(adminId);

        blockTrade.setAdmin(admin);
        blockTrade.setAuditStatus(AuditStatus.PASS_AUDIT);//管理员直接通过审核

        return addBlockTrade(blockTrade);
    }

}

package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.BlockTradeDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.CreditIdentification;
import cn.beerate.model.ModelValidate;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.BlockTradeService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BlockTradeServiceImpl extends ItemCommonServiceImpl<t_item_block_trade> implements BlockTradeService {
    private BlockTradeDao blockTradeDao;

    public BlockTradeServiceImpl(BlockTradeDao blockTradeDao) {
        super(blockTradeDao);
        this.blockTradeDao = blockTradeDao;
    }

    /**
     * 添加大宗交易
     */
    @Transactional
    public Message<t_item_block_trade> addBlockTrade(t_item_block_trade blockTrade){
        //参数校验
        Message<String> messageValid = ModelValidate.blockTradeValid(blockTrade);
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

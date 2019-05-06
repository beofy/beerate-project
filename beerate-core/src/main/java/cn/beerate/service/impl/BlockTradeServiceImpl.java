package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.BlockTradeDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ModelValidate;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.service.BlockTradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BlockTradeServiceImpl extends BaseServiceImpl<t_item_block_trade> implements BlockTradeService {
    private BlockTradeDao blockTradeDao;

    public BlockTradeServiceImpl(BlockTradeDao blockTradeDao) {
        super(blockTradeDao);
        this.blockTradeDao = blockTradeDao;
    }

    /**
     * 添加大宗交易
     */
    public Message<t_item_block_trade> addBlockTrade(t_item_block_trade blockTrade){
        //参数校验
        Message<String> messageValid = ModelValidate.blockTradeValid(blockTrade);
        if(messageValid.fail()){
            return Message.error(messageValid.getMsg());
        }

       return Message.success(blockTradeDao.save(blockTrade));

    }

    /**
     *  添加大宗交易-前台用户
     */
    public Message<t_item_block_trade> addBlockTradeByUser(t_item_block_trade blockTrade,long userId){
        blockTrade.getUser().setId(userId);
        blockTrade.setAuditStatus(AuditStatus.WAIT_AUDIT);//设置审核状态-等待审核

        return addBlockTrade(blockTrade);
    }

    /**
     *  添加大宗交易-后台管理员
     */
    public Message<t_item_block_trade> addBlockTradeByAdmin(t_item_block_trade blockTrade,long adminId){
        blockTrade.getAdmin().setId(adminId);
        blockTrade.setAuditStatus(AuditStatus.PASS_AUDIT);//管理员直接通过审核

        return addBlockTrade(blockTrade);
    }

}

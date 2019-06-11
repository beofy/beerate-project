package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.ItemCommonDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ItemModel;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.service.ItemCommonService;

import java.util.Date;

public class ItemCommonServiceImpl<T extends ItemModel> extends BaseServiceImpl<T> implements ItemCommonService<T> {

    private ItemCommonDao<T> itemCommonDao;
    public <D extends ItemCommonDao<T>> ItemCommonServiceImpl(D d) {
        super(d);
        this.itemCommonDao = d;
    }

    protected Message<String> itemModelValid(ItemModel itemModel) {
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

        if (itemModel.getAuditStatus() == AuditStatus.NONE) {
            return Message.error("项目审核状态不正确");
        }

        return Message.ok("校验成功");
    }


    @Override
    public Message<t_item_loan> auditItem(AuditStatus auditStatus, long itemId) {
        ItemModel itemModel = itemCommonDao.getOne(itemId);
        if (itemModel.getAuditStatus()!=AuditStatus.WAIT_AUDIT){
            return Message.error(String.format("项目状态：[%s]", itemModel.getAuditStatus().getValue()));
        }

        itemModel.setAuditStatus(auditStatus);
        return null;
    }
}

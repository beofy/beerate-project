package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ItemModel;

public interface ItemCommonService<T extends ItemModel> extends IBaseService<T> {

    Message<T> addItem(T t);

    Message<T> addItemByUser(T t, long userId);

    Message<T> addItemByAdmin(T t, long adminId);

    /**
     * 项目审核
     */
    Message<T> auditItem(AuditStatus auditStatus, String description, long itemId);

}

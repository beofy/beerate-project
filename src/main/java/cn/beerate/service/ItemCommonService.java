package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ItemModel;

public interface ItemCommonService<T extends ItemModel> extends IBaseService<T> {

    /**
     * 添加项目
     */
    Message<T> addItem(T t);

    /**
     * 添加项目-前台用户
     */
    Message<T> addItemByUser(T t, long userId);

    /**
     * 添加项目-后台用户
     */
    Message<T> addItemByAdmin(T t, long adminId);

    /**
     * 项目审核
     */
    Message<T> auditItem(AuditStatus auditStatus, String description, long itemId);

    /**
     * 项目分配
     */
    Message<T>  addItemAssigner(long adminId,long itemId);

}

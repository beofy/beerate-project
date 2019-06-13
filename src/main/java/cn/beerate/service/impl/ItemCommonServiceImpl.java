package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.ItemCommonDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ItemModel;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.ItemCommonService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class ItemCommonServiceImpl<T extends ItemModel> extends BaseServiceImpl<T> implements ItemCommonService<T> {

    private ItemCommonDao<T> itemCommonDao;

    public <D extends ItemCommonDao<T>> ItemCommonServiceImpl(D d) {
        super(d);
        this.itemCommonDao = d;
    }

    Message<String> itemModelValid(ItemModel itemModel) {
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
    @Transactional
    public Message<T> addItem(T t) {
        //参数校验
        Message<String> messageValid = itemModelValid(t);
        if(messageValid.fail()){
            return Message.error(messageValid.getMsg());
        }

        //设置审核描述
        t.setDescription("");

        return Message.success(itemCommonDao.save(t));
    }

    @Override
    @Transactional
    public Message<T> addItemByUser(T t, long userId) {
        t_user user = new t_user();
        user.setId(userId);

        t.setUser(user);
        t.setAuditStatus(AuditStatus.WAIT_AUDIT);//设置审核状态-等待审核

        return addItem(t);
    }

    @Override
    @Transactional
    public Message<T> addItemByAdmin(T t, long adminId) {
        t_admin admin = new t_admin();
        admin.setId(adminId);

        t.setAdmin(admin);
        t.setAuditStatus(AuditStatus.PASS_AUDIT);//管理员直接通过审核

        return addItem(t);
    }

    @Override
    @Transactional
    public  Message<T> auditItem(AuditStatus auditStatus, String description, long itemId) {
        T t = itemCommonDao.getOne(itemId);
        if (t.getAuditStatus() != AuditStatus.WAIT_AUDIT) {
            return Message.error(String.format("项目状态：[%s]", t.getAuditStatus().getValue()));
        }

        t.setAuditStatus(auditStatus);
        t.setDescription(description);

        if (super.save(t)==null){
            return Message.error("审核失败，请重试");
        }

        return Message.success(t);
    }

    @Override
    public Message<T> addItemAssigner(long adminId, long itemId) {
        T t = itemCommonDao.getOne(itemId);

        if (t.getAdmin()!=null){
            return Message.error("项目已分配");
        }

        t_admin admin  = new t_admin();
        admin.setId(adminId);
        t.setAdmin(admin);

        if (itemCommonDao.save(t)==null){
            return Message.error("分配失败");
        }

        return Message.success(t);
    }
}

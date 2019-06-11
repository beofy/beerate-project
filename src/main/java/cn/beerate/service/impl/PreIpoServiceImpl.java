package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.PreIpoDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ModelValidate;
import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.model.entity.t_user;
import cn.beerate.service.PreIpoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PreIpoServiceImpl extends ItemCommonServiceImpl<t_item_pre_ipo> implements PreIpoService {

    private PreIpoDao preIpoDao;

    public PreIpoServiceImpl(PreIpoDao preIpoDao) {
        super(preIpoDao);
        this.preIpoDao = preIpoDao;
    }

    @Transactional
    public Message<t_item_pre_ipo> addPreIpo( t_item_pre_ipo preIpo){
        Message<String> message =  ModelValidate.preIpoValid(preIpo);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.success(preIpoDao.save(preIpo));
    }

    @Transactional
    public Message<t_item_pre_ipo> addPreIpoByUser( t_item_pre_ipo preIpo,long userId){
        t_user user = new t_user();
        user.setId(userId);

        preIpo.setUser(user);
        preIpo.setAuditStatus(AuditStatus.WAIT_AUDIT);

        return addPreIpo(preIpo);
    }

    @Transactional
    public Message<t_item_pre_ipo> addPreIpoByAdmin( t_item_pre_ipo preIpo,long adminId){
        t_admin admin = new t_admin();
        admin.setId(adminId);

        preIpo.setAdmin(admin);
        preIpo.setAuditStatus(AuditStatus.PASS_AUDIT);

        return addPreIpo(preIpo);
    }
}

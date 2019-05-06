package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.PreIpoDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.ModelValidate;
import cn.beerate.model.entity.t_item_pre_ipo;
import cn.beerate.service.PreIpoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PreIpoServiceImpl extends BaseServiceImpl<t_item_pre_ipo> implements PreIpoService {

    private PreIpoDao preIpoDao;

    public PreIpoServiceImpl(PreIpoDao preIpoDao) {
        super(preIpoDao);
        this.preIpoDao = preIpoDao;
    }

    public Message<t_item_pre_ipo> addPreIpo( t_item_pre_ipo preIpo){
        Message<String> message =  ModelValidate.preIpoValid(preIpo);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.success(preIpoDao.save(preIpo));
    }

    public Message<t_item_pre_ipo> addPreIpoByUser( t_item_pre_ipo preIpo,long userId){
        preIpo.getUser().setId(userId);
        preIpo.setAuditStatus(AuditStatus.WAIT_AUDIT);

        return addPreIpo(preIpo);
    }
    public Message<t_item_pre_ipo> addPreIpoByAdmin( t_item_pre_ipo preIpo,long adminId){
        preIpo.getAdmin().setId(adminId);
        preIpo.setAuditStatus(AuditStatus.PASS_AUDIT);

        return addPreIpo(preIpo);
    }
}

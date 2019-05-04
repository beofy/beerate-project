package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.PreIpoDao;
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
        // TODO: 2019/5/4   设置初始化字段参数

        return Message.success(preIpoDao.save(preIpo));
    }

    public Message<t_item_pre_ipo> addPreIpoByUser( t_item_pre_ipo preIpo,long userId){
        preIpo.getUser().setId(userId);

        return addPreIpo(preIpo);
    }
    public Message<t_item_pre_ipo> addPreIpoByAdmin( t_item_pre_ipo preIpo,long adminId){
        preIpo.getAdmin().setId(adminId);

        return addPreIpo(preIpo);
    }
}

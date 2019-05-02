package cn.beerate.service.impl;

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
}

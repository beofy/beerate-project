package cn.beerate.service.impl;

import cn.beerate.dao.OverseasListingDao;
import cn.beerate.model.entity.t_item_overseas_listing;
import cn.beerate.service.OverseasListingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OverseasListingServiceImpl extends BaseServiceImpl<t_item_overseas_listing> implements OverseasListingService {

    private OverseasListingDao overseasListingDao;
    public OverseasListingServiceImpl(OverseasListingDao overseasListingDao) {
        super(overseasListingDao);
        this.overseasListingDao = overseasListingDao;
    }
}

package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.OverseasListingDao;
import cn.beerate.model.NetProfit;
import cn.beerate.model.entity.t_item_overseas_listing;
import cn.beerate.service.OverseasListingService;
import org.apache.commons.lang3.StringUtils;
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

    private Message<String> overseasListingValid(t_item_overseas_listing overseasListing){

        if(StringUtils.isBlank(overseasListing.getRealName())){
            return Message.error("请填写真实姓名");
        }

        if(StringUtils.isBlank(overseasListing.getMobile())){
            return Message.error("请填写手机号码");
        }

        if(StringUtils.isBlank(overseasListing.getJob())){
            return Message.error("请填写职位");
        }

        if(StringUtils.isBlank(overseasListing.getCompanyName())){
            return Message.error("请填写企业名称");
        }

        if(StringUtils.isBlank(overseasListing.getCompanyBusiness())){
            return Message.error("请填写企业主营业务");
        }

        if(overseasListing.getNetProfit()== NetProfit.NONE){
            return Message.error("请选择净利润情况");
        }

        return Message.ok("校验成功");
    }


    @Transactional
    public Message<t_item_overseas_listing> addOverseasListing(t_item_overseas_listing overseasListing){

        Message<String> message = overseasListingValid(overseasListing);
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.success(overseasListingDao.save(overseasListing));
    }

}

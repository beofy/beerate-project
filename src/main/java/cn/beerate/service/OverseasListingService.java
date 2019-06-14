package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_overseas_listing;

public interface OverseasListingService extends IBaseService<t_item_overseas_listing>{
    Message<t_item_overseas_listing> addOverseasListing(t_item_overseas_listing overseasListing);

    Message<t_item_overseas_listing> editItemIsShow(long overseasListingId);
}

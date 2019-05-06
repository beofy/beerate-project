package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_overseas_listing;

public interface OverseasListingService {

    Message<t_item_overseas_listing> addOverseasListing(t_item_overseas_listing overseasListing);
}

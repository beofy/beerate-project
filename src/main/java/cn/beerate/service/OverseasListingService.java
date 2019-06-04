package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_overseas_listing;
import org.springframework.stereotype.Service;

public interface OverseasListingService {
    Message<t_item_overseas_listing> addOverseasListing(t_item_overseas_listing overseasListing);
}

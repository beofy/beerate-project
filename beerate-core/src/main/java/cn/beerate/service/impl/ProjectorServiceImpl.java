package cn.beerate.service.impl;

import cn.beerate.service.*;
import org.springframework.stereotype.Service;

@Service
public class ProjectorServiceImpl implements ProjectorService {

    private UserBusinessService userBusinessService;
    private UserAttentionService userAttentionService;
    private UserConcatService userConcatService;
    private UserVisitorService userVisitorService;
    private ItemDeliveryService itemDeliveryService;
    private ItemMessageBoardService itemMessageBoardService;

    public ProjectorServiceImpl(UserBusinessService userBusinessService, UserAttentionService userAttentionService, UserConcatService userConcatService, UserVisitorService userVisitorService, ItemDeliveryService itemDeliveryService, ItemMessageBoardService itemMessageBoardService) {
        this.userBusinessService = userBusinessService;
        this.userAttentionService = userAttentionService;
        this.userConcatService = userConcatService;
        this.userVisitorService = userVisitorService;
        this.itemDeliveryService = itemDeliveryService;
        this.itemMessageBoardService = itemMessageBoardService;
    }

    /**
     * 项目方列表
     */
    //public Message<>






}

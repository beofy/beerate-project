package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.model.dto.ProjectorList;
import cn.beerate.service.*;
import org.springframework.data.domain.Page;
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
    @Override
    public Message<Page<ProjectorList>> pageOfProjectList(int page, int size, String column, String order){

        return Message.success(null);
    }

    /**
     * 项目投递列表
     */
    public Message pageOfItemDeliveryList(){


        return null;
    }




}

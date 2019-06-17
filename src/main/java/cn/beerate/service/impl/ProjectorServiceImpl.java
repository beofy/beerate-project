package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.model.dto.Projector;
import cn.beerate.service.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProjectorServiceImpl implements ProjectorService {

    private UserBusinessService userBusinessService;
    private UserAttentionService userAttentionService;
    private UserConcatService userConcatService;
    private UserVisitorService userVisitorService;

    public ProjectorServiceImpl(UserBusinessService userBusinessService, UserAttentionService userAttentionService, UserConcatService userConcatService, UserVisitorService userVisitorService) {
        this.userBusinessService = userBusinessService;
        this.userAttentionService = userAttentionService;
        this.userConcatService = userConcatService;
        this.userVisitorService = userVisitorService;
    }

    /**
     * 项目方列表
     */
    @Override
    public Message<Page<Projector>> pageOfProjectList(int page, int size, String column, String order){

        return Message.success(null);
    }




}

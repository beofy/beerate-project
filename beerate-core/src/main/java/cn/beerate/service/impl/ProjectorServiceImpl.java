package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.model.dto.ProjectorList;
import cn.beerate.model.entity.Qt_item_delivery;
import cn.beerate.model.entity.Qt_user_business;
import cn.beerate.model.entity.Qt_user_visitor;
import cn.beerate.service.*;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
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
        Qt_user_business userBusiness = Qt_user_business.t_user_business;

        Expression<ProjectorList> expression = Projections.constructor(
                ProjectorList.class,
                userBusiness.user.id,
                userBusiness.name,
                userBusiness.company,
                userBusiness.title,
                JPAExpressions.select(Qt_user_visitor.t_user_visitor.count()).from(Qt_user_visitor.t_user_visitor).where(Qt_user_visitor.t_user_visitor.visitor_user.id.eq(userBusiness.user.id)),
                JPAExpressions.select(Qt_item_delivery.t_item_delivery.count()).from(Qt_item_delivery.t_item_delivery).where(Qt_item_delivery.t_item_delivery.delivery_user.id.eq(userBusiness.user.id))
        );

        return Message.success(userBusinessService.page(page,size,column,order,expression,null));
    }

    /**
     * 项目投递列表
     */
    public Message pageOfItemDeliveryList(){


        return null;
    }






}

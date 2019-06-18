package cn.beerate.dao.impl;


import cn.beerate.model.dto.Projector;
import cn.beerate.model.dto.ProjectorDetail;
import cn.beerate.model.dto.UserBusiness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserBusinessRepository {
    /**
     * 查找用户的名片信息
     */
    UserBusiness findUserBusinessDetailByUser(long userId);

    /**
     * 项目方列表
     */
    Page<Projector> pageOfProjector(Pageable pageable);

    /**
     * 项目简介
     */
    Projector projectorIntro();

    /**
     * 项目详情
     */
    ProjectorDetail projectorDetail();

}

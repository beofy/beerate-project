package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.ItemType;
import cn.beerate.model.dto.Projector;
import cn.beerate.model.dto.ProjectorDetail;
import cn.beerate.model.dto.ProjectorIntro;
import cn.beerate.model.dto.UserBusiness;
import cn.beerate.model.entity.t_user_business;
import org.springframework.data.domain.Page;

import java.io.InputStream;

public interface UserBusinessService extends IBaseService<t_user_business>{

    /**
     *解析名片
     */
    t_user_business parse(InputStream inputStream);

    /**
     * 添加名片信息
     */
    Message<t_user_business> addUserBusiness(t_user_business userBusiness , long userId);

    /**
     * 补充用户名片资料
     */
    Message<t_user_business> supplementUserBusiness(ItemType investPrefer, String aboutText, String workText, long userId);

    /**
     * 查询用户名片详细信息
     */
    Message<UserBusiness> findUserBusinessDetail(long userId);

    /**
     * 更新用户名片信息
     */
    Message<t_user_business> updateUserBusiness(t_user_business business,long userId);

    /**
     * 项目方列表
     */
    Page<Projector> pageOfProjector(int page, int size, String column, String order);

    /**
     * 项目简介
     */
    Message<ProjectorIntro> projectorIntro();

    /**
     * 项目详情
     */
    Message<ProjectorDetail> projectorDetail();

}

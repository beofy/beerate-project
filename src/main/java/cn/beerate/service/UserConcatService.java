package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.Projector;
import cn.beerate.model.entity.t_user_contact;
import org.springframework.data.domain.Page;

public interface UserConcatService extends IBaseService<t_user_contact> {

    /**
     * 添加用户联系
     */
    Message<t_user_contact> addUserContact(long userId, long contactUserId);

    /**
     * 项目方联系
     */
    Page<Projector> contact(int page, int size, String column, String order, long userId);

    /**
     * 联系项目方
     */
    Page<Projector> beContact(int page, int size, String column, String order, long contactUserId);

}

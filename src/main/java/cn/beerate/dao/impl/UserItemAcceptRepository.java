package cn.beerate.dao.impl;

import cn.beerate.model.dto.UserItemAccept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserItemAcceptRepository {

    /**
     * 我收到的项目
     */
    Page<UserItemAccept> userItemAccept(Pageable pageable, long userId);
}

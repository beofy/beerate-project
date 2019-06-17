package cn.beerate.dao.impl;

import cn.beerate.model.dto.UserItemCollect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemCollectRepository {

    Page<UserItemCollect> userItemCollect(Pageable pageable, long userId);

}

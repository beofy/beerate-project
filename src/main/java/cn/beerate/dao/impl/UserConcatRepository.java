package cn.beerate.dao.impl;

import cn.beerate.model.dto.Projector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserConcatRepository {

    Page<Projector> contact(Pageable pageable, long userId);

    Page<Projector> beContact(Pageable pageable, long contactUserId);
}

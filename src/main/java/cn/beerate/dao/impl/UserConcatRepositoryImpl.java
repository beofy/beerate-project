package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.Projector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class UserConcatRepositoryImpl implements UserConcatRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<Projector> contact(Pageable pageable, long userId) {
        return null;
    }

    @Override
    public Page<Projector> beContact(Pageable pageable, long contactUserId) {
        return null;
    }
}

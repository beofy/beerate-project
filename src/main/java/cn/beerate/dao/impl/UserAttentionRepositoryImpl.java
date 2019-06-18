package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.Projector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class UserAttentionRepositoryImpl implements UserAttentionRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<Projector> attention(Pageable pageable, long userId) {
        return null;
    }

    @Override
    public Page<Projector> beAttention(Pageable pageable, long attentionUserId) {
        return null;
    }
}

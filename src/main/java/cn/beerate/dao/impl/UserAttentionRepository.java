package cn.beerate.dao.impl;

import cn.beerate.model.dto.Projector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAttentionRepository {

    Page<Projector> attention(Pageable pageable, long userId);

    Page<Projector> beAttention(Pageable pageable, long attentionUserId);
}

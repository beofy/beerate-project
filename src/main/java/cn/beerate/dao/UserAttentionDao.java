package cn.beerate.dao;

import cn.beerate.dao.impl.UserAttentionRepository;
import cn.beerate.model.entity.t_user_attention;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttentionDao extends IBaseDao<t_user_attention>, UserAttentionRepository {

    t_user_attention findByUserIdAndAttentionUserId(long userId,long attentionUserId);

}

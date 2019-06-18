package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.Projector;
import cn.beerate.model.entity.t_user_attention;
import org.springframework.data.domain.Page;

public interface UserAttentionService extends IBaseService<t_user_attention> {

    /**
     * 添加用户关注
     */
    Message<t_user_attention> addUserAttention(long userId, long attentionUserId);

    /**
     * 是否关注
     */
    t_user_attention isAttention(long userId, long attentionUserId);

    /**
     * 取消关注
     */
    Message<t_user_attention> delAttention(long userId,long attentionUserId);

    /**
     * 项目方的关注
     */
    Page<Projector> attention(int page, int size, String column, String order, long userId);

    /**
     * 被关注的项目方
     */
    Page<Projector> beAttention(int page, int size, String column, String order, long attentionUserId);

}

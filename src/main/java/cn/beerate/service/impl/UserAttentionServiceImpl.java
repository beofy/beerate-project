package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.UserAttentionDao;
import cn.beerate.model.dto.Projector;
import cn.beerate.model.entity.t_user_attention;
import cn.beerate.service.UserAttentionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserAttentionServiceImpl extends BaseServiceImpl<t_user_attention> implements UserAttentionService {

    private UserAttentionDao userAttentionDao;

    public UserAttentionServiceImpl(UserAttentionDao userAttentionDao) {
        super(userAttentionDao);
        this.userAttentionDao = userAttentionDao;
    }

    @Override
    public Message<t_user_attention> addUserAttention(long userId, long attentionUserId) {
        t_user_attention isUserAttention = isAttention(userId, attentionUserId);
        if (isUserAttention != null) {
            return Message.error("已关注过了");
        }

        t_user_attention userAttention = new t_user_attention();
        userAttention.setUserId(userId);
        userAttention.setAttentionUserId(attentionUserId);

        if (userAttentionDao.save(userAttention) == null) {
            return Message.error("保存失败");
        }

        return Message.success(userAttention);
    }

    @Override
    public t_user_attention isAttention(long userId, long attentionUserId) {
        return userAttentionDao.findByUserIdAndAttentionUserId(userId, attentionUserId);
    }

    @Override
    public Page<Projector> attention(int page, int size, String column, String order, long userId) {
        return userAttentionDao.attention(getPageable(page, size, column, order),userId);
    }

    @Override
    public Page<Projector> beAttention(int page, int size, String column, String order, long attentionUserId) {
        return userAttentionDao.beAttention(getPageable(page, size, column, order),attentionUserId);
    }
}

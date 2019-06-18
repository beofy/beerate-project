package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.UserConcatDao;
import cn.beerate.model.dto.Projector;
import cn.beerate.model.entity.t_user_contact;
import cn.beerate.service.UserConcatService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserConcatServiceImpl extends BaseServiceImpl<t_user_contact> implements UserConcatService {
    private UserConcatDao userConcatDao;

    public UserConcatServiceImpl(UserConcatDao userConcatDao) {
        super(userConcatDao);
        this.userConcatDao = userConcatDao;
    }

    @Override
    public Message<t_user_contact> addUserContact(long userId, long contactUserId) {
        t_user_contact userContact = new t_user_contact();
        userContact.setUserId(userId);
        userContact.setContactUserId(contactUserId);

        if (userConcatDao.save(userContact)==null){
            return Message.error("保存失败");
        }

        return Message.success(userContact);
    }

    @Override
    public Page<Projector> contact(int page, int size, String column, String order, long userId) {
        return userConcatDao.contact(getPageable(page, size, column, order),userId);
    }

    @Override
    public Page<Projector> beContact(int page, int size, String column, String order, long contactUserId) {
        return userConcatDao.beContact(getPageable(page, size, column, order),contactUserId);
    }
}

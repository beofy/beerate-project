package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_user_business;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserBusinessService extends IBaseService<t_user_business>{

    /**
     *上传用户名片
     */
    Message<String> uploadUserBusiness(String tempFilePath,String userFilePath,long userId);

}

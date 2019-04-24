package cn.beerate.service.impl;

import cn.beerate.PropertiesHodler;
import cn.beerate.common.Message;
import cn.beerate.dao.UserDao;
import cn.beerate.model.entity.t_user;
import cn.beerate.security.Encrypt;
import cn.beerate.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends BaseServiceImpl<t_user>  implements UserService {
    private UserDao userDao;
    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    /**
     *
     * @param mobile 手机号
     * @param password 密码
     * @param registeIp 注册ip
     * @param registeChannel 注册渠道
     */
    @Transactional
    public Message<t_user> registe(String mobile ,String password,String registeIp ,String registeChannel){
        //判断手机号是否存在
        if(userDao.findByMobile(mobile)!=null){
            return Message.error("手机号已经存在");
        }

        t_user user = new t_user();
        //随机生成用户名
        user.setMobile(mobile);
        user.setUsername(RandomStringUtils.randomAlphanumeric(13));
        user.setPassword(Encrypt.MD5(password+ PropertiesHodler.properties.getSecurityProperties().getPassword_md5_salt()));
        user.setEmail("");
        user.setIs_allow_login(true);
        user.setLast_login_client("");
        user.setLast_login_ip("");
        user.setLogin_count(0);
        user.setPassword_continue_fails(0);
        user.setRegiste_channel(registeChannel);
        user.setRegiste_ip(registeIp);

        t_user user1 = userDao.save(user);
        if(user1==null){
            return Message.error("注册失败");
        }

        return Message.success(user1);
    }
}

package cn.beerate.service.impl;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.dao.UserDao;
import cn.beerate.model.entity.t_user;
import cn.beerate.request.ChannelType;
import cn.beerate.security.Encrypt;
import cn.beerate.service.UserService;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<t_user>  implements UserService {
    private UserDao userDao;
    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    /**
     * @param mobile 手机号
     * @param password 密码
     * @param regIp 注册ip
     * @param regChannel 注册渠道
     */
    @Transactional
    public Message<t_user> registe(String mobile ,String password,String regIp ,String regChannel,String sessionId){
        //判断手机号是否存在
        if(userDao.findByMobile(mobile)!=null){
            return Message.error("手机号已经存在");
        }

        t_user user = new t_user();
        //随机生成用户名
        user.setCreateTime(new Date());
        user.setMobile(mobile);
        user.setUsername(RandomStringUtils.randomAlphanumeric(13));
        user.setPassword(Encrypt.MD5(password+ PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt()));
        user.setEmail("");
        user.setPhoto("");
        user.setIs_allow_login(true);
        user.setLast_login_client("");
        user.setLast_login_ip("");
        user.setLogin_count(0);
        user.setPassword_continue_fails(0);
        ChannelType channelType = EnumUtils.getEnumIgnoreCase(ChannelType.class,regChannel);
        user.setReg_channel(channelType!=null?channelType:ChannelType.UNKNOWN);
        user.setReg_ip(regIp);
        user.setSessionId(sessionId);

        t_user user1 = userDao.save(user);
        if(user1==null){
            return Message.error("注册失败");
        }

        return Message.success(user1);
    }

    /**
     *
     * @param mobile 手机号
     * @param password 密码
     * @param loginIp 登录ip
     * @param loginChannel 登录渠道
     */
    @Transactional
    public Message<t_user> login(String mobile,String password,String loginIp,String loginChannel,String sessionId){
        t_user user = userDao.findByMobile(mobile);
        if(user==null){
            return Message.error("用户名或者密码错误");
        }

        //用户是否允许登录
        if(!user.getIs_allow_login()){
            return Message.error("用户已锁定");
        }

        //密码错误锁定时间
        if(user.getPassword_locked_time()!=null&&user.getPassword_locked_time().after(new Date())){
            return Message.error("密码错误次过多，请稍微再试");
        }

        //判断密码
        String md5PassWord =Encrypt.MD5(password+ PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt());
        if(!user.getPassword().equals(md5PassWord)){
            user.setPassword_continue_fails(user.getPassword_continue_fails()+1);
            if(user.getPassword_continue_fails()>10){
                //锁定30分钟
                user.setPassword_locked_time(DateUtils.addMinutes(new Date(),30));
            }
            userDao.save(user);

            return Message.error("用户名或者密码错误");
        }

        user.setPassword_continue_fails(0);//重置密码错误次数
        user.setLast_login_time(new Date());//登录时间
        user.setLogin_count(user.getLogin_count()+1);//登录次数
        user.setLast_login_client(loginChannel);//登录渠道
        user.setLast_login_ip(loginIp);//登录ip
        user.setSessionId(sessionId);

        if (userDao.save(user)==null){
            return Message.error("登录失败");
        }

        return Message.success(user);

    }

    @Transactional
    public Message<t_user> updateUserPhoto(String photo,long userId){
        t_user user = userDao.getOne(userId);
        user.setPhoto(photo);

        if (userDao.save(user)==null){
            return Message.error("更新失败");
        }

        return Message.success(user);
    }

    @Override
    @Transactional
    public Message<t_user> updateUserPassWord(String oldPwd, String newPwd, long userId) {
        t_user user = userDao.getOne(userId);
        if (!user.getPassword().equals(Encrypt.MD5(oldPwd+PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt()))){
            return Message.error("密码不正确");
        }
        //设置新密码
        user.setPassword(Encrypt.MD5(newPwd+PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt()));

        if (userDao.save(user)==null){
            return Message.error("密码更改失败");
        }

        return Message.success(user);
    }

    @Override
    @Transactional
    public Message<t_user> updateUserMobile(String mobile, long userId) {
        if (userDao.findByMobile(mobile)!=null){
            return Message.error("手机号已绑定");
        }

        t_user user = userDao.getOne(userId);
        user.setMobile(mobile);
        if (userDao.save(user)==null){
            return Message.error("手机号更改失败");
        }

        return Message.success(user);
    }

    @Override
    @Transactional
    public Message<t_user> updateUserEmail(String email, long userId) {
        if (userDao.findByEmail(email)!=null){
            return Message.error("邮箱已绑定");
        }

        t_user user = userDao.getOne(userId);
        user.setEmail(email);
        if (userDao.save(user)==null){
            return Message.error("邮箱更改失败");
        }

        return Message.success(user);
    }

    @Override
    @Transactional
    public Message<t_user> resetUserPassword(String mobile,String newPwd) {
        t_user user = userDao.findByMobile(mobile);

        user.setPassword(Encrypt.MD5(newPwd+PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt()));
        user.setPassword_continue_fails(0);//重置密码错误次数
        user.setPassword_locked_time(null);//重置密码锁定时间
        if (userDao.save(user)==null){
            return Message.error("密码重置失败");
        }

        return Message.success(user);
    }
}

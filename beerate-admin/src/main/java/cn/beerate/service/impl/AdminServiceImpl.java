package cn.beerate.service.impl;

import cn.beerate.PropertiesHodler;
import cn.beerate.common.Message;
import cn.beerate.dao.AdminDao;
import cn.beerate.model.entity.t_admin;
import cn.beerate.security.Encrypt;
import cn.beerate.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminServiceImpl extends BaseServiceImpl<t_admin> implements AdminService {

    private AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        super(adminDao);
        this.adminDao = adminDao;
    }

    /**
     * 后台用户登陆
     */
    public Message<t_admin> login(String username,String password,String ipAddr){
        t_admin admin = adminDao.findByUsernameAndPassword(username,Encrypt.MD5(password+ PropertiesHodler.properties.getSecurityProperties().getPassword_md5_salt()));
        if(admin==null){
           return Message.error("用户不存在或者密码错误");
        }

        //判断账号时候锁定
        if(admin.getLockStatus()){
           return Message.error("账号已锁定");
        }

        //增加登录次数
        admin.setLoginCount(admin.getLoginCount()+1);
        //设置登录ip
        admin.setLastLoginIp(ipAddr);
        //设置登录时间
        admin.setLastLoginTime(new Date());
        admin.setPhoto("");
        admin.setRemark("");

        return Message.success(adminDao.save(admin));
    }

    @Override
    public Message<String> addAdmin(t_admin admin,long createid) {

        //判断用户名是否存在
        if(adminDao.findByUsername(admin.getUsername())!=null){
            return Message.error("管理账号已存在");
        }

        //添加创建者id
        admin.setCreaterId(createid);
        admin.setPassword(Encrypt.MD5(admin.getPassword()+ PropertiesHodler.properties.getSecurityProperties().getPassword_md5_salt()));
        admin.setLastLoginIp("");
        admin.setLockStatus(false);
        admin.setLoginCount(0L);

        if(super.save(admin)==null){
            return Message.error("管理员添加失败");
        }

        return Message.ok("管理员添加成功");
    }


}

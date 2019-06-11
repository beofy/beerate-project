package cn.beerate.service.impl;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.dao.AdminDao;
import cn.beerate.model.entity.t_admin;
import cn.beerate.security.Encrypt;
import cn.beerate.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<t_admin> implements AdminService {
    private AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        super(adminDao);
        this.adminDao = adminDao;
    }

    @Override
    @Transactional
    public Message<t_admin> login(String username,String password,String ipAddr){
        t_admin admin = adminDao.findByUsername(username);
        if(admin==null){
           return Message.error("用户不存在或者密码错误");
        }

        String  md5Password=Encrypt.MD5(password+ PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt());
        if(!admin.getPassword().equals(md5Password)){
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
        //修改登录信息
        adminDao.save(admin);

        return Message.success(admin);
    }


    @Override
    @Transactional
    public Message<t_admin> addAdmin(t_admin admin,long creatorId){

        //判断用户名是否存在
        if(adminDao.findByUsername(admin.getUsername())!=null){
            return Message.error("管理账号已存在");
        }

        admin.setCreateTime(new Date());
        //添加创建者id
        admin.setCreatorId(creatorId);
        admin.setPassword(Encrypt.MD5(admin.getPassword()+ PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt()));
        admin.setLastLoginIp("");
        admin.setLockStatus(false);
        admin.setLoginCount(0L);

        if (adminDao.save(admin)==null){
            return Message.error("管理员添加失败");
        }

        return Message.success(admin);
    }


    @Override
    @Transactional
    public Message<t_admin> updateAdminPassWord(String oldPwd, String newPwd, long adminId) {
        t_admin admin = adminDao.getOne(adminId);
        String encOldPassWod = Encrypt.MD5(oldPwd+ PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt());
        if(!admin.getPassword().equals(encOldPassWod)){
            return Message.error("密码错误");
        }

        //更新密码
        String encNwePassWord = Encrypt.MD5(newPwd+ PropertiesHolder.properties.getSecurityProperties().getPassword_md5_salt());
        admin.setPassword(encNwePassWord);

        if (adminDao.save(admin)==null){
            return Message.error("密码修改失败");
        }

        return Message.success(admin);
    }

    @Override
    @Transactional
    public Message<t_admin> updateAdmin(t_admin admin, long adminId) {
        t_admin admin1 = adminDao.getOne(adminId);
        if (admin1==null){
            return Message.error("管理员不存在");
        }

        //不能修改用户名
        admin1.setRealityName(admin.getRealityName());
        admin1.setEmail(admin.getEmail());
        admin1.setMobile(admin.getMobile());
        admin1.setDepartment(admin.getDepartment());
        admin1.setPosition(admin.getPosition());
        admin1.setRemark(admin.getRemark());

        if (adminDao.save(admin1)==null){
            return Message.error("修改失败");
        }

        return Message.success(admin1);
    }
}

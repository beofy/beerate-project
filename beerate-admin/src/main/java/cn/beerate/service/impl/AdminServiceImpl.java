package cn.beerate.service.impl;

import cn.beerate.PropertiesHodler;
import cn.beerate.Utils.PathUtil;
import cn.beerate.common.Message;
import cn.beerate.dao.AdminDao;
import cn.beerate.model.entity.t_admin;
import cn.beerate.security.Encrypt;
import cn.beerate.service.AdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<t_admin> implements AdminService {
    private final Log logger = LogFactory.getLog(this.getClass());
    private AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        super(adminDao);
        this.adminDao = adminDao;
    }

    /**
     * 后台用户登陆
     */
    @Transactional
    public Message<t_admin> login(String username,String password,String ipAddr){
        t_admin admin = adminDao.findByUsername(username);
        if(admin==null){
           return Message.error("用户不存在或者密码错误");
        }
        String  md5Password=Encrypt.MD5(password+PropertiesHodler.properties.getSecurityProperties().getPassword_md5_salt());
        
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

        return Message.success(adminDao.save(admin));
    }


    @Override
    @Transactional
    public Message<String> addAdmin(t_admin admin,long createid){

        //判断用户名是否存在
        if(adminDao.findByUsername(admin.getUsername())!=null){
            return Message.error("管理账号已存在");
        }

        admin.setCreateTime(new Date());
        //添加创建者id
        admin.setCreaterId(createid);
        admin.setPassword(Encrypt.MD5(admin.getPassword()+ PropertiesHodler.properties.getSecurityProperties().getPassword_md5_salt()));
        admin.setLastLoginIp("");
        admin.setLockStatus(false);
        admin.setLoginCount(0L);

        //保存头像
        if(!StringUtils.isEmpty(admin.getPhoto())){
            String file = PathUtil.getStaticPath()+admin.getPhoto();
            File in = new File(file);
            File out= new File(PathUtil.getAdminPath(),in.getName());
            try {
                if (!out.getParentFile().exists()){
                    if(out.getParentFile().mkdirs()){
                        logger.info("创建管理员资源文件夹");
                    }
                }

                FileCopyUtils.copy(in,out);
            } catch (IOException e) {
                logger.error(e);
                return Message.error("系统异常");
            }
            admin.setPhoto(PropertiesHodler.properties.getFileProperties().getAdminFile()+File.separator+out.getName());
        }

        if(super.save(admin)==null){
            return Message.error("管理员添加失败");
        }

        return Message.ok("管理员添加成功");
    }


}

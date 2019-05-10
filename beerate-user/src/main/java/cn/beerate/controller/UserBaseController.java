package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.constant.SessionKey;
import cn.beerate.model.bean.User;
import cn.beerate.security.Encrypt;

public class UserBaseController extends BaseController{

    /**
     * 获取当前用户
     */
    public User getUser(){
        return getSessionAttr(SessionKey.USER_SESSION_KEY,User.class);
    }

    /**
     * 获取当用户id
     */
    long getUserId(){
        String userId = Encrypt.decrypt3DES(getUser().getToken(),PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key());

        return Long.parseLong(userId);
    }

    /**
     * 获取用户名
     */
    public String getUserName(){
        return getUser().getName();
    }

    /**
     * 获取用户头像
     */
    public String getAdminPhoto(){
        return getUser().getPhoto();
    }
}

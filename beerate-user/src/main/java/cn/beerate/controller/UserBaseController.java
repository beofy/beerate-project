package cn.beerate.controller;

import cn.beerate.constant.SessionKey;
import cn.beerate.model.bean.User;

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
        return getUser().getId();
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

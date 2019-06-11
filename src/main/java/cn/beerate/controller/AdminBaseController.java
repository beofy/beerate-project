package cn.beerate.controller;

import cn.beerate.constant.SessionKey;
import cn.beerate.model.entity.t_admin;

class AdminBaseController extends BaseController{

    /**
     * 获取当前登录管理员
     */
    public t_admin getAdmin(){
       return getSessionAttr(SessionKey.ADMIN_SESSION_KEY,t_admin.class);
    }

    /**
     * 获取当前管理员id
     */
    long getAdminId(){
        return getAdmin().getId();
    }

    /**
     * 获取管理员用户名
     */
    public String getAdminName(){
        return getAdmin().getUsername();
    }

    /**
     *   登出
     */
    public void signOut(){
        super.getSession().removeAttribute(SessionKey.ADMIN_SESSION_KEY);
    }
}

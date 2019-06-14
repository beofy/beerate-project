package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.IToken;
import cn.beerate.common.Token;
import cn.beerate.constant.SessionKey;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.bean.User;
import cn.beerate.model.entity.t_user;

public class UserBaseController extends BaseController{


    /**
     * 设置会话用户信息
     */
    protected User setUser(t_user user){
        User currUser =  new User(user.getId(),user.getUsername(),user.getPhoto(),user.getMobile(),user.getEmail(),false);
        if(user.getUser_business()!=null&&user.getUser_business().getAuditStatus()== AuditStatus.PASS_AUDIT){
            currUser.setApprove(true);
        }

        return currUser;
    }

    /**
     * 保存登录状态
     */
    public void setUserSession(t_user user){
        super.getSession().setAttribute(SessionKey.USER_SESSION_KEY,setUser(user));
    }

    /**
     * 移除用户会话认证
     */
    public void removeUserSession(){
        getSession().removeAttribute(SessionKey.USER_SESSION_KEY);
    }

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

    /**
     * 获取token令牌
     */
    public IToken getIToken(long userId){
        return new Token(userId, PropertiesHolder.properties.getSecurityProperties().getSession_time_out());
    }
}

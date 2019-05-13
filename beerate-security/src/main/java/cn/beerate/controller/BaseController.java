package cn.beerate.controller;

import cn.beerate.request.ChannelType;
import cn.beerate.session.CacheSession;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    /**
     * 获取当前请求IP
     */
    String getIp() {
        return this.request.getRemoteHost();
    }

    /**
     * 获取request对象
     */
    protected HttpServletRequest getRequest() {
        return this.request;
    }

    /**
     * 获取session对象
     */
    HttpSession getSession() {
        return this.request.getSession();
    }

    /**
     * 获取response对象
     */
    HttpServletResponse getResponse() {
        return this.response;
    }

    /**
     * 获取sessino保存的信息
     */
    @SuppressWarnings("unchecked")
    <T> T getSessionAttr(String name, Class<T> tClass) {
        Object o = getSession().getAttribute(name);
        return (T) o;
    }

    /**
     * 获取访问的渠道
     */
    public ChannelType getChannel(){
        CacheSession cacheSeesion = (CacheSession)getSession();
        return EnumUtils.getEnumIgnoreCase(ChannelType.class,cacheSeesion.getChannel());
    }
}

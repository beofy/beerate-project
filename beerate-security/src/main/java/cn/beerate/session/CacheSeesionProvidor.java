package cn.beerate.session;


import cn.beerate.cache.Cache;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * CacheSeesion提供者
 */
public class CacheSeesionProvidor implements CacheSession {

    /**
     * 渠道类型
     */
    private String channel;
    /**
     * 唯一标识码
     */
    private String channelId;

    public CacheSeesionProvidor(String channel, String channelId) {
        this.channel = channel;
        this.channelId = channelId;

        initCacheSession();
    }

    /**
     * 初始当前会话的cachesession,没有则创建的一个空的hashmap进行会话缓存
     */
    private void initCacheSession(){
        if(Cache.get(this.channelId)==null){
            Cache.set(channelId,new HashMap<String,Object>(),"1h");
        }
    }

    /**
     * 获取渠道类型
     */
    @Override
    public String getChannel() {
        return channel;
    }

    /**
     * 获取会话的sessionid
     */
    @Override
    public String getId() {
        return channelId;
    }

    @Override
    public Object getAttribute(String name) {
        Map attribute = Cache.get(this.channelId, Map.class);

        return attribute.get(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setAttribute(String name, Object value) {
        Map attribute = Cache.get(this.channelId,Map.class);

        attribute.put(name,value);
    }

    @Override
    public void removeAttribute(String name) {
        Map attribute = Cache.get(this.channelId, Map.class);
        attribute.remove(name);
    }

    //====================================上述方式适用于基于非web实现=====================================


    //====================================以下方法在非web场景下不可用=====================================
    /**
     * 会话创建时间
     */
    @Override
    @Deprecated
    public long getCreationTime() {
        return 0;
    }

    @Override
    @Deprecated
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    @Deprecated
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    @Deprecated
    public void setMaxInactiveInterval(int interval) {

    }

    @Override
    @Deprecated
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    @Deprecated
    public HttpSessionContext getSessionContext() {
        return null;
    }



    @Override
    @Deprecated
    public Object getValue(String name) {
        return null;
    }

    @Override
    @Deprecated
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    @Deprecated
    public String[] getValueNames() {
        return new String[0];
    }

    @Override
    @Deprecated
    public void putValue(String name, Object value) {

    }


    @Override
    @Deprecated
    public void removeValue(String name) {

    }

    @Override
    @Deprecated
    public void invalidate() {

    }

    @Override
    @Deprecated
    public boolean isNew() {
        return false;
    }
}

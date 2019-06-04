package cn.beerate.session;


import cn.beerate.PropertiesHolder;
import cn.beerate.cache.Cache;
import org.apache.catalina.session.StandardSessionFacade;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * CacheSeesion提供者
 */
public class CacheSession extends StandardSessionFacade implements ICacheSession {

    /**
     * 渠道类型
     */
    private String channel;
    /**
     * 唯一标识码
     */
    private String channelId;

    public CacheSession(HttpSession session, String channel, String channelId){
        super(session);
        this.channel = channel;
        this.channelId = channelId;

        initCacheSession();
    }


    /**
     * 初始当前会话的cacheSession,没有则创建的一个空的hashMap进行会话缓存
     */
    private void initCacheSession(){
        if(Cache.get(this.channelId)==null){
            Cache.set(channelId,new HashMap<String,Object>(), PropertiesHolder.properties.getSecurityProperties().getSession_time_out());
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


}

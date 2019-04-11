package cn.beerate.session;


import javax.servlet.http.HttpSession;

/**
 * 缓存session接口
 */
interface CacheSession extends HttpSession {

    /**
     * 获取渠道类型
     */
    String getChannel();
}

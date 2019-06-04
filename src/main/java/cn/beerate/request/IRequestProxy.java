package cn.beerate.request;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求适配器
 */
interface IRequestProxy extends HttpServletRequest {
    /**
     * 访问渠道
     */
    String CHANNEL = "channel";

    /**
     * 唯一标识码
     */
    String CHANNELID = "channelId";

}

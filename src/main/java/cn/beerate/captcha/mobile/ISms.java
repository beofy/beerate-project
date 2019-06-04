package cn.beerate.captcha.mobile;

import java.util.List;

/**
 * 短信接口
 */
public interface ISms {

    /**
     * 发送一条短信
     * @param mobile 手机号
     * @param SMScontent 短信内容
     */
    void sendSMS(String mobile,String SMScontent);


    /**
     * 发送一条短信
     * @param mobiles 手机号
     * @param SMScontent 短信内容
     */
    void sendSMS(List<String> mobiles, String SMScontent);

}

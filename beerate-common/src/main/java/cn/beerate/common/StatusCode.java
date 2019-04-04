package cn.beerate.common;

public class StatusCode {
    /** 公共成功码200:处理成功 */
    public static final int SUCCESS = 200;

    /** 公共错误码 -1:处理失败 */
    public static final int ERROR = -1;

    /** 公共错误码-404:未找到资源() */
    public static final int ERROR_404 = -404;

    /** 公共错误码-500:程序错误 */
    public static final int ERROR_500 = -500;

    /** 拦截标识-100:登录拦截 */
    public static final int NOT_LOGIN = -100;

    /** 登录超时*/
    public static final int  LOGIN_TIME_OUT = -102;
}

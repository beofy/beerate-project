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

    /** token校验异常 */
    public static final int  TOKEN_EXCEPTION = -103;

    /** 错误：请输入登录验证码 */
    public static final int  NEED_LOGIN_CAPTCHA = -104;

    /** 错误：登录验证码错误 */
    public static final int  LOGIN_CAPTCHA_ERROR = -105;

    /** 用户未认证 */
    public static final int NOT_APPROVE=-201;
}

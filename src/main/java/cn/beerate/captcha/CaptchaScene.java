package cn.beerate.captcha;

/**
 * 验证码场景
 */
public enum CaptchaScene {

    /**
     * 管理员登录
     */
    ADMIN_LOGIN,
    /**
     * 用户登录
     */
    USER_LOGIN,
    /**
     * 用户注册
     */
    USER_REGIST,
    /**
     * 更新手机号
     */
    UPDATE_USER_MOBILE,

    /**
     * 更新邮箱
     */
    UPDATE_USER_EMAIL,
    /**
     * 用户密码重置
     */
    RESET_USER_PASSWORD;

}

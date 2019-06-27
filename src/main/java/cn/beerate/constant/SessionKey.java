package cn.beerate.constant;

/**
 * session常量
 */
public interface SessionKey {

    /**
     * 管理员登录会话:(SESSION键值)
     */
    String ADMIN_SESSION_KEY="admin_approve_session";

    /**
     * 管理员权限:(SESSION键值)
     */
    String ADMIN_SESSION_RIGHT="admin_approve_right";

    /**
     * 用户登录会话:(SESSION键值)
     */
    String USER_SESSION_KEY="user_approve_session";

    /**
     * 用户登录验证码错误key
     */
    String USER_LOGIN_FAIL_COUNT="user_login_password_fail_key";

}

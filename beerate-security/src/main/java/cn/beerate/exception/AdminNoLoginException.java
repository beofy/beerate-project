package cn.beerate.exception;

/**
 * 管理员未登录异常
 */
public class AdminNoLoginException extends Exception{

    public AdminNoLoginException(String message) {
        super(message);
    }
}

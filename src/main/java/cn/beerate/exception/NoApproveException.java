package cn.beerate.exception;

/**
 * 用户认证异常
 */
public class NoApproveException extends Exception{
    public NoApproveException(String message) {
        super(message);
    }
}

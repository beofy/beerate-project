package cn.beerate.Utils;

/**
 * 字符工具类
 */
public class StringUtil {

    /**
     * 字符是不是邮箱
     */
    public static boolean isEmail(String email){
        if(email==null){
            return false;
        }
        return email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }

    /**
     * 是不是手机
     */
    public static boolean isMobile(String mobile){
        if(mobile==null){
            return false;
        }

        return mobile.matches("^(((13[0-9]{1})|(15[0-35-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\\d{8})$");
    }

}

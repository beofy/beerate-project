package cn.beerate.utils;

import java.util.UUID;

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

    /**
     * 校验并生成新的文件名
     */
    public static String generateFileName(String originalFilename){
        if (originalFilename==null){
            throw new IllegalArgumentException("源文件名不存在");
        }

        if (!originalFilename.contains(".")){
            throw new IllegalArgumentException("文件名没有后缀");
        }

        String[] str = originalFilename.split("\\.");
        if (str.length>2) {
            throw new IllegalArgumentException("不安全的文件类型");
        }

        return UUID.randomUUID().toString()+"."+str[1];
    }

}

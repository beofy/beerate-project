package cn.beerate.utils;

import cn.beerate.PropertiesHolder;
import cn.beerate.security.Encrypt;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * 令牌工具类
 */
public class TokenUtil {

    public static String createToken(String sign, int expireTime) {
        String token = sign + "," + DateUtils.addMilliseconds(new Date(), expireTime).getTime()*1000;
        return Encrypt.encrypt3DES(token, PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key());
    }

    public static String validToken(String token) {
        String result = Encrypt.decrypt3DES(token, PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key());

        String[] tokens = result.split(",");
        if (new Date().getTime() > Long.parseLong(tokens[1])) {

            return null;
        }

        return tokens[0];

    }


}

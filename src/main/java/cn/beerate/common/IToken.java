package cn.beerate.common;

import cn.beerate.PropertiesHolder;
import cn.beerate.exception.TokenException;
import cn.beerate.security.Encrypt;

import java.util.Date;

public interface IToken extends OAuth2{

    /**
     * 校验访问令牌
     */
    static void validToken(String token,long userId) throws TokenException{
            String result = Encrypt.decrypt3DES(token, PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key());
            String[] tokens = result.split(",");

            if (tokens.length<1){
                throw new TokenException("token错误");
            }

            if (new Date().getTime() > Long.parseLong(tokens[1])) {
                throw new TokenException("请求超时");
            }

            if (userId!=Long.parseLong(tokens[0])){
                throw new TokenException("无效请求");
            }
    };


}

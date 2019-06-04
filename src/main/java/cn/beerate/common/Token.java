package cn.beerate.common;

import cn.beerate.PropertiesHolder;
import cn.beerate.cache.Cache;
import cn.beerate.security.Encrypt;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class Token implements IToken {

    private final long userId;
    private final Date expireTime;
    private final String token;


    public Token(long userId, String expireIn) {
        this.userId = userId;
        this.expireTime = DateUtils.addMilliseconds(new Date(), Cache.parseDuration(expireIn)*1000);

        this.token = createToken();
    }

    private String createToken() {
        String token = userId + "," + this.expireTime.getTime();
        return Encrypt.encrypt3DES(token, PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key());
    }

    @Override
    public String getAccess_token() {
        return this.token;
    }

    @Override
    public Long getExpires_in() {
        long pool = (this.expireTime.getTime() - new Date().getTime())/1000;
        if (pool < 0) {
            return null;
        }
        return pool;
    }

    @Override
    public String getToken_type() {
        return "";
    }

    @Override
    public String getRefresh_token() {
        return "";
    }

    @Override
    public String getScope() {
        return "";
    }
}

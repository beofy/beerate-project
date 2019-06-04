package cn.beerate.common;

public interface OAuth2 {

    /**
     * 获取token
     */
    String getAccess_token();

    /**
     * 有效时间
     */
    Long getExpires_in();

    String getToken_type();

    String getRefresh_token();

    String getScope();

}

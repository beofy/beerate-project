package cn.beerate.oss;

import java.time.LocalDateTime;

/**
 * 文件信息
 */
public class OssObject {

    public OssObject( String sessionId,String fileName, String uri,String url, int expireIn) {
        this.sessionId = sessionId;
        this.fileName = fileName;
        this.uri = uri;
        this.url=url;
        this.expireIn = expireIn;
        this.expireTime = LocalDateTime.now().plusSeconds(this.expireIn);
    }

    /**
     * 上传文件会话的sessionId
     */
    private String  sessionId ;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 资源标识
     */
    private String uri;

    /**
     * 绝对路径
     */
    private String url;

    /**
     * 超时时间（秒）
     */
    private int  expireIn;

    /**
     * 超时时间
     */
    private LocalDateTime expireTime;

    /**
     * 是否到期
     */
    public boolean checkExpireIn() {
        return LocalDateTime.now().isBefore(expireTime);
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUri() {
        return uri;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public String getUrl() {
        return url;
    }
}

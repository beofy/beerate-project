package cn.beerate.oss;

import java.time.LocalDateTime;

/**
 * 文件信息
 */
public class FileInfo {

    public FileInfo(Long id, String fileName, String uri, int expireIn, OWNER owner) {
        this.id = id;
        this.fileName = fileName;
        this.uri = uri;
        this.expireIn = expireIn;
        this.expireTime = LocalDateTime.now().plusSeconds(this.expireIn);
        this.owner = owner;
    }

    /**
     * 用户id
     */
    private Long id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 资源标识
     */
    private String uri;

    /**
     * 超时时间（秒）
     */
    private int  expireIn;

    /**
     * 超时时间
     */
    private LocalDateTime expireTime;

    /**
     * 拥有者类型
     */
    private OWNER owner;

    enum OWNER{
        USER,
        ADMIN
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public OWNER getOwner() {
        return owner;
    }

    public void setOwner(OWNER owner) {
        this.owner = owner;
    }

    /**
     * 是否到期
     */
    public boolean checkExpireIn() {
        return LocalDateTime.now().isBefore(expireTime);
    }
}

package cn.beerate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统文件参数
 */
@Configuration
@ConfigurationProperties(prefix = "cn.beerate.file")
public class FileProperties {

    /**
     * 临时目录
     */
    private String tempFile;

    /**
     * 管理员用户文件
     */
    private String adminFile;

    /**
     * 前台用户文件
     */
    private String userFile;

    private String fileStoreImpl;

    public String getFileStoreImpl() {
        return fileStoreImpl;
    }

    public void setFileStoreImpl(String fileStoreImpl) {
        this.fileStoreImpl = fileStoreImpl;
    }

    public String getTempFile() {
        return tempFile;
    }

    public void setTempFile(String tempFile) {
        this.tempFile = tempFile;
    }

    public String getAdminFile() {
        return adminFile;
    }

    public void setAdminFile(String adminFile) {
        this.adminFile = adminFile;
    }

    public String getUserFile() {
        return userFile;
    }

    public void setUserFile(String userFile) {
        this.userFile = userFile;
    }
}

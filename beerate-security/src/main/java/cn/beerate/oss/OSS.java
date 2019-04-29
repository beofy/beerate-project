package cn.beerate.oss;

import cn.beerate.common.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件存储业务类
 */
public interface OSS {

    /**
     * 上传文件
     */
    Message<FileInfo>  uploadFile(File file) throws IOException;

    Message<FileInfo>  uploadFile(InputStream in) throws IOException;

    Message<FileInfo>  uploadFile(byte[] bytes) throws IOException;

    /**
     * 下载文件
     */
    Message<FileInfo> downLoadFile(String uri);

}

package cn.beerate.oss;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 文件存储业务类
 */
public interface OSS {

    /**
     * 上传文件
     */
    void  uploadFile(InputStream in ,String url) throws IOException;

    /**
     * 下载文件
     */
    URL downLoadFile(String uri) throws MalformedURLException, URISyntaxException;

    /**
     * 初始化方法
     */
    void init();
}

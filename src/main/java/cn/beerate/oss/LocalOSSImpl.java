package cn.beerate.oss;

import cn.beerate.PropertiesHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 本地文件存储实现
 */
public class LocalOSSImpl implements OSS {

    private final Log logger = LogFactory.getLog(this.getClass());

    /**
     * 本地存储以项目为根目录
     */
    @Override
    public String getRoot() {
        try {
            return ResourceUtils.getURL("").getPath();
        } catch (FileNotFoundException e) {
            logger.error("OSS根路劲获取异常");
        }

        return null;
    }

    @Override
    public void uploadFile(InputStream in,String out) throws IOException{
        FileCopyUtils.copy(in,new FileOutputStream(new File(out)));
    }

    @Override
    public URL downLoadFile(String uri) throws MalformedURLException,URISyntaxException{
       return new URI("file:///"+uri).toURL();
    }

    public void init(){
        File tempDir = new File(PropertiesHolder.properties.getFileProperties().getTempFile());
        if(tempDir.mkdirs()){
            logger.info(String.format("创建临时文件目录:%s",tempDir.getAbsolutePath()));
        }

        File userDir = new File(PropertiesHolder.properties.getFileProperties().getUserFile());
        if(userDir.mkdirs()){
            logger.info(String.format("创建用户文件目录:%s",userDir.getAbsolutePath()));
        }

        File adminDir = new File(PropertiesHolder.properties.getFileProperties().getAdminFile());
        if(adminDir.mkdirs()){
            logger.info(String.format("创建管理员文件目录:%s",adminDir.getAbsolutePath()));
        }
    }
}

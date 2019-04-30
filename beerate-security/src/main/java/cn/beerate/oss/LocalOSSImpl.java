package cn.beerate.oss;

import cn.beerate.utils.PathUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;

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

    public void init(){
        File tempDir = new File(PathUtil.getTempPath());
        if(tempDir.mkdirs()){
            logger.info(String.format("创建临时文件目录:%s",PathUtil.getTempPath()));
        }

        File userDir = new File(PathUtil.getUserPath());
        if(userDir.mkdirs()){
            logger.info(String.format("创建用户文件目录:%s",PathUtil.getUserPath()));
        }

        File adminDir = new File(PathUtil.getAdminPath());
        if(adminDir.mkdirs()){
            logger.info(String.format("创建管理员文件目录:%s",PathUtil.getAdminPath()));
        }
    }

    @Override
    public void uploadFile(InputStream in,String out) throws IOException{
        FileCopyUtils.copy(in,new FileOutputStream(new File(out)));
    }

    @Override
    public URL downLoadFile(String uri) throws MalformedURLException,URISyntaxException{
       return new URI("file:///"+uri).toURL();
    }
}

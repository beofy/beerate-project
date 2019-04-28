package cn.beerate.Utils;

import cn.beerate.PropertiesHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 路径工具
 */
public class PathUtil {
    private static final Log logger = LogFactory.getLog(PathUtil.class);
    /**
     * 获取类根路径
     */
    public static String getClassPath()  {
        try {
            return ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            logger.info("获取类路劲异常");
            return null;
        }
    }

    /**
     * 获取静态资源路径
     */
    public static String getStaticPath() {
        return getClassPath()+File.separator+"static";
    }

    /**
     * 获取临时资源路劲
     */
    public static String getTempPath(){
        return getStaticPath()+ File.separator+ PropertiesHolder.properties.getFileProperties().getTempFile();
    }


    /**
     * 获取管理资源路劲
     */
    public static String getAdminPath(){
        return getStaticPath()+ File.separator+ PropertiesHolder.properties.getFileProperties().getAdminFile();
    }

    /**
     * 获取用户资源路劲
     */
    public static String getUserPath(){
        return getStaticPath()+ File.separator+ PropertiesHolder.properties.getFileProperties().getUserFile();
    }
}

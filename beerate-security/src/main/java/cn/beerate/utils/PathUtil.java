package cn.beerate.utils;

import cn.beerate.PropertiesHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * 路径工具
 */
public class PathUtil {

    public static String getPath(String url)  {
        try {
            return ResourceUtils.getURL(url).getPath();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * 获取临时资源路劲
     */
    public static String getTempPath(){
        return getPath(PropertiesHolder.properties.getFileProperties().getTempFile());
    }

    /**
     * 获取管理资源路劲
     */
    public static String getAdminPath(){
        return getPath(PropertiesHolder.properties.getFileProperties().getAdminFile());
    }

    /**
     * 获取用户资源路劲
     */
    public static String getUserPath(){
        return getPath(PropertiesHolder.properties.getFileProperties().getUserFile());
    }
}

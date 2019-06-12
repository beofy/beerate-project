package cn.beerate.utils;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class PathUtil {

    /**
     * 获取项目路劲
     */
    public static String getRoot(){
        try {
            return ResourceUtils.getURL("").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}

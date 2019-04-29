package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.Utils.PathUtil;
import cn.beerate.common.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 文件控制器
 */
@Controller
@RequestMapping(value = {"/admin/file/","/user/file/"})
public class FileUploadController extends BaseController{
    private final Log logger = LogFactory.getLog(this.getClass());

    /**
     * 文件上传
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public Message<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        //临时文件夹
        String tempFile = PropertiesHolder.properties.getFileProperties().getTempFile();
        //临时文件名
        String tempFileName = UUID.randomUUID().toString();
        //临时静态资源相对路劲
        String urlStaticPath = tempFile+PathUtil.SPRIT+tempFileName;

        File localFile = new File(PathUtil.getTempPath(), tempFileName);
        if(!localFile.exists()){
            if(localFile.getParentFile().mkdirs()){
                logger.info("创建临时文件夹");
            }
        }
        file.transferTo(localFile);

        return Message.success(urlStaticPath);
    }

    /**
     * 文件下载
     */
    @GetMapping("/downLoadFile")
    public void downLoadFile(){
    }

}

package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.oss.FileInfo;
import cn.beerate.oss.OSS;
import cn.beerate.security.Encrypt;
import cn.beerate.common.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件控制器
 */
@Controller
@RequestMapping(value = {"/admin/file/","/user/file/"})
public class FileUploadController extends BaseController{
    private ObjectMapper objectMapper;
    private OSS oss;

    public FileUploadController(ObjectMapper objectMapper, OSS oss) {
        this.objectMapper = objectMapper;
        this.oss = oss;
    }

    /**
     * 文件上传
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public Message<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        Message<FileInfo> fileInfoMessage = oss.uploadFile(file.getInputStream());
        String  fileInfoJson = objectMapper.writeValueAsString(fileInfoMessage);

        return Message.success(Encrypt.encrypt3DES(fileInfoJson, PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key()));
    }

    /**
     * 文件下载
     */
    @GetMapping("/downLoadFile")
    public void downLoadFile(){
    }

    /**
     * 文件预览
     */
    @GetMapping("/previewFile/{fileInfo}")
    public void previewFile(@PathVariable("fileInfo") String fileInfo) throws Exception{
        //String fileInfoJson = Encrypt.decryptSES(fileInfo,PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key());
        //FileInfo fileInfo1 = JSONObject.parseObject(fileInfoJson,FileInfo.class);
    }

}

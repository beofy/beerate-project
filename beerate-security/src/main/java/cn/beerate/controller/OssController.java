package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.oss.OssObject;
import cn.beerate.oss.OSS;
import cn.beerate.security.Encrypt;
import cn.beerate.common.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 文件控制器
 */
@Controller
@RequestMapping()
public class OssController extends BaseController{
    private ObjectMapper objectMapper;
    private OSS oss;

    public OssController(ObjectMapper objectMapper, OSS oss) {
        this.objectMapper = objectMapper;
        this.oss = oss;
    }

    /**
     * 文件上传
     */
    @PostMapping(value={"/admin/upload","/user/upload"})
    @ResponseBody
    public Message<Map<String,String>> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        //组装OssObject参数
        String fileName = UUID.randomUUID().toString();
        String uri = "/"+PropertiesHolder.properties.getFileProperties().getTempFile()+fileName;
        String url = oss.getRoot()+ PropertiesHolder.properties.getFileProperties().getTempFile() +fileName;
        OssObject ossObject =  new OssObject(getSession().getId(),fileName,uri,url,3600);

        //上传文件
        oss.uploadFile(file.getInputStream(),url);

        Map<String,String> map = new HashMap<>();
        //加密存储,previewUri,用于预览
        map.put("previewUri",uri);
        //加密存储oss信息，ossInfo用于其他api
        map.put("ossInfo",Encrypt.encrypt3DES(objectMapper.writeValueAsString(ossObject),PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key()));

        return Message.success(map);
    }

    /**
     * 图片预览
     */
    @GetMapping(value={"${cn.beerate.file.temp-file}/*"
            ,"${cn.beerate.file.temp-file}/*"
            ,"${cn.beerate.file.user-file}/*"
    })
    @ResponseBody
    public Message image() throws Exception {
        getResponse().addHeader("Content-Type", "image/jpeg");
        URL url =oss.downLoadFile(oss.getRoot()+getRequest().getRequestURI());
        try(ServletOutputStream servletOutputStream = getResponse().getOutputStream()) {
            IOUtils.write(IOUtils.toByteArray(new FileInputStream(new File(url.toURI()))),servletOutputStream);
        }

        return null;
    }



}

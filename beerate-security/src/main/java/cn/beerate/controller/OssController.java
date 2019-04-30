package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.oss.OssObject;
import cn.beerate.oss.OSS;
import cn.beerate.security.Encrypt;
import cn.beerate.common.Message;
import cn.beerate.utils.PathUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.UUID;


/**
 * 文件控制器
 */
@Controller
@RequestMapping(value = {"/admin/file/","/user/file/"})
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
    @PostMapping("/uploadFile")
    @ResponseBody
    public Message<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = UUID.randomUUID().toString();
        String uri = PropertiesHolder.properties.getFileProperties().getTempFile()+fileName;
        String url = PathUtil.getTempPath()+fileName;
        OssObject ossObject =  new OssObject(getSession().getId(),fileName,uri,url,3600);

        //上传文件
        oss.uploadFile(file.getInputStream(),url);

        //转为json字符串
        String  ossJson = objectMapper.writeValueAsString(ossObject);

        return Message.success(Encrypt.encrypt3DES(ossJson, PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key()));
    }

    /**
     * 文件下载
     */
    @GetMapping("/downLoad/{uri}")
    public void downLoadFile(@PathVariable String uri){

    }

    /**
     * 图片预览
     */
    @GetMapping("/previewImage/{ossInfo}")
    @ResponseBody
    public Message previewFile(@PathVariable String ossInfo) throws Exception {
        String ossInfoJson = Encrypt.decrypt3DES(ossInfo,PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key());
        OssObject ossObject = JSONObject.parseObject(ossInfoJson,OssObject.class);

        //验证会话
        if(!getSession().getId().equals(ossObject.getSessionId())){
            throw new IllegalArgumentException("参数异常，请重试");
        }

        //文件是否过期
        if(!ossObject.checkExpireIn()){
            throw new IllegalArgumentException("文件已过期");
        }

        //输出图片
        getResponse().addHeader("Content-Type", "image/jpeg");

        URL url =oss.downLoadFile(ossObject.getUrl());

        try(ServletOutputStream servletOutputStream = getResponse().getOutputStream()) {
            IOUtils.write(IOUtils.toByteArray(new FileInputStream(new File(url.toURI()))),servletOutputStream);
        }

        return null;
    }

}

package cn.beerate.oss;

import cn.beerate.PropertiesHolder;
import cn.beerate.security.Encrypt;
import com.alibaba.fastjson.JSONObject;

/**
 * 对象存储工具
 */
public class OssObjectUtil {
    /**
     * 校验
     */
    public static OssObject validate(String ossInfo,String sessionId){
        String ossInfoJson = Encrypt.decrypt3DES(ossInfo, PropertiesHolder.properties.getSecurityProperties().getDes_encrypt_key());
        OssObject ossObject = JSONObject.parseObject(ossInfoJson,OssObject.class);

        //验证会话
        if(!sessionId.equals(ossObject.getSessionId())){
            throw new IllegalArgumentException("参数异常，请重试");
        }

        //文件是否过期
        if(!ossObject.checkExpireIn()){
            throw new IllegalArgumentException("文件已过期");
        }

        return ossObject;
    }

}

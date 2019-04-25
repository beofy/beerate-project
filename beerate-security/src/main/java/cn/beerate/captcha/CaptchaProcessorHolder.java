package cn.beerate.captcha;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CaptchaProcessorHolder {

    private Map<String,CaptchaProcessor> captchaProcessorMap;
    public CaptchaProcessorHolder(Map<String, CaptchaProcessor> captchaProcessorMap) {
        this.captchaProcessorMap = captchaProcessorMap;
    }

    /**
     * 查找一个验证处理器
     * @param captcha 验证码类型枚举
     */
    public CaptchaProcessor getCaptchaProcessor(Captcha captcha){
       for(String key :captchaProcessorMap.keySet()){
           CaptchaProcessor processor = captchaProcessorMap.get(key);
           if(processor.support(captcha)){
               return processor;
           }
       }

       return null;
    }

}

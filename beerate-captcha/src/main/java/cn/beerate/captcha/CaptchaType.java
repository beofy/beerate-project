package cn.beerate.captcha;


import cn.beerate.captcha.image.ImageCaptcha;
import cn.beerate.captcha.image.ImageCaptchaProcessor;


public enum CaptchaType {

    IMAGE("image",new ImageCaptchaProcessor()){
        @Override
        public CaptchaGenerator getCaptchaGenerator(String captchaCode, int expireIn) {
            return new ImageCaptcha(captchaCode,expireIn,null);
        }
    },
    SMS("sms",null){
        @Override
        public CaptchaGenerator getCaptchaGenerator(String captchaCode, int expireIn) {
            return null;
        }
    },
    EMAIL("email",null){
        @Override
        public CaptchaGenerator getCaptchaGenerator(String captchaCode, int expireIn) {
            return null;
        }
    };

    /**
     * 验证码类型
     */
    private String type;

    /**
     * 验证码处理器
     */
    private CaptchaProcessor captchaProcessor;

    CaptchaType(String type, CaptchaProcessor captchaProcessor) {
        this.type = type;
        this.captchaProcessor = captchaProcessor;
    }

    public abstract CaptchaGenerator getCaptchaGenerator(String captchaCode, int expireIn);


    public static CaptchaType getCaptchaType(String type){
        CaptchaType[]  captchaTypes = CaptchaType.values();
        for (CaptchaType captchaType : captchaTypes) {
            if(captchaType.type.equalsIgnoreCase(type)){
                return captchaType;
            }
        }

        return null;
    }


    public String getType() {
        return type;
    }

    public CaptchaProcessor getCaptchaProcessor() {
        return captchaProcessor;
    }

}

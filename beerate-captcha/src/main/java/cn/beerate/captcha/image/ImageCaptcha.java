package cn.beerate.captcha.image;

import cn.beerate.captcha.CaptchaCode;


import java.awt.image.BufferedImage;

/**
 * 图形验证码
 */
public class ImageCaptcha extends CaptchaCode {

    public ImageCaptcha(String captchaCode, int expireIn, BufferedImage bufferedImage) {
        super(captchaCode, expireIn);
        this.bufferedImage = bufferedImage;
    }

    /**
     * 验证码图片
     */
    private BufferedImage bufferedImage;

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}

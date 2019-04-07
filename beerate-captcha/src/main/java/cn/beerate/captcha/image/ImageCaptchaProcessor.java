package cn.beerate.captcha.image;


import cn.beerate.captcha.AbstractCaptchaProcessor;
import cn.beerate.captcha.CaptchaGenerator;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

public class ImageCaptchaProcessor extends AbstractCaptchaProcessor{

    @Override
    public void send(ServletWebRequest servletWebRequest,CaptchaGenerator imageCaptchaProcessor) throws IOException {
        servletWebRequest.getResponse().addHeader("Content-Type", "image/jpeg");
        ImageCaptcha imageCaptcha = (ImageCaptcha)imageCaptchaProcessor;

        //生成图形验证码
        BufferedImage captchaImage = kaptcha().createImage(imageCaptcha.getCaptchaCode());

        //输出验证码
        ImageIO.write(captchaImage, "JPEG", servletWebRequest.getResponse().getOutputStream());
    }



    public DefaultKaptcha kaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties prop = new Properties();
        prop.setProperty("kaptcha.border", "yes");
        prop.setProperty("kaptcha.border.color", "105,179,90");
        prop.setProperty("kaptcha.textproducer.font.color", "blue");
        prop.setProperty("kaptcha.image.width", "125");
        prop.setProperty("kaptcha.image.height", "45");
        prop.setProperty("kaptcha.session.key", "code");
        prop.setProperty("kaptcha.textproducer.char.length", "4");
        prop.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config conf = new Config(prop);
        kaptcha.setConfig(conf);
        return kaptcha;
    }
}

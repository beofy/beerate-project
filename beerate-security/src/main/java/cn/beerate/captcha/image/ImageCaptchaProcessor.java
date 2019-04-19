package cn.beerate.captcha.image;


import cn.beerate.captcha.AbstractCaptchaProcessor;
import cn.beerate.captcha.Captcha;
import cn.beerate.captcha.CaptchaGenerator;
import cn.beerate.captcha.CaptchaProcessor;
import cn.beerate.common.Message;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

@Component
public class ImageCaptchaProcessor extends AbstractCaptchaProcessor implements CaptchaProcessor {

    @Override
    public Message<String> send(HttpServletRequest request, HttpServletResponse response, CaptchaGenerator imageCaptchaProcessor) throws IOException {
        response.addHeader("Content-Type", "image/jpeg");

        //生成图形验证码
        BufferedImage captchaImage = kaptcha().createImage(imageCaptchaProcessor.getCaptchaCode());

        //输出验证码
        ImageIO.write(captchaImage, "JPEG", response.getOutputStream());

        return null;
    }

    @Override
    public boolean support(Captcha captcha) {
        return Captcha.IMAGE==captcha;
    }

    private DefaultKaptcha kaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties prop = new Properties();
        prop.setProperty("kaptcha.border", "yes");
        prop.setProperty("kaptcha.border.color", "255,255,255");
        prop.setProperty("kaptcha.textproducer.font.color", "blue");
        prop.setProperty("kaptcha.image.width", "125");
        prop.setProperty("kaptcha.image.height", "45");
//        prop.setProperty("kaptcha.session.key", "code");
//        prop.setProperty("kaptcha.textproducer.char.length", "4");
        prop.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config conf = new Config(prop);
        kaptcha.setConfig(conf);
        return kaptcha;
    }
}

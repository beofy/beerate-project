package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.constant.PlatformSettingKey;
import cn.beerate.service.PlatformSettingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/platform/")
public class SettingController extends AdminBaseController {

    private PlatformSettingService platformSettingService;

    @Autowired
    public SettingController(PlatformSettingService platformSettingService) {
        this.platformSettingService = platformSettingService;
    }

    @GetMapping("/setting.html")
    public String platformSetting(Model model) {
        model.addAttribute(PlatformSettingKey.SMS_ACCOUNT,platformSettingService.findBySetKey(PlatformSettingKey.SMS_ACCOUNT));
        model.addAttribute(PlatformSettingKey.SMS_PASSWORD,platformSettingService.findBySetKey(PlatformSettingKey.SMS_PASSWORD));
        model.addAttribute(PlatformSettingKey.MAIL_ACCOUNT,platformSettingService.findBySetKey(PlatformSettingKey.MAIL_ACCOUNT));
        model.addAttribute(PlatformSettingKey.MAIL_PASSWORD,platformSettingService.findBySetKey(PlatformSettingKey.MAIL_PASSWORD));
        model.addAttribute(PlatformSettingKey.SMTP_SERVER,platformSettingService.findBySetKey(PlatformSettingKey.SMTP_SERVER));
        model.addAttribute(PlatformSettingKey.SMTP_PORT,platformSettingService.findBySetKey(PlatformSettingKey.SMTP_PORT));

        return "platform/setting";
    }

    @PostMapping("/updateSmsSetting")
    @ResponseBody
    public Message<String> updateSmsSetting(String smsAccount, String smsPassword) {
        if (StringUtils.isBlank(smsAccount)){
            return Message.ok("请输入短信账号");
        }
        if (StringUtils.isBlank(smsPassword)){
            return Message.ok("请输入短信账号密码");
        }
        platformSettingService.updateSetValueByKey(PlatformSettingKey.SMS_ACCOUNT, smsAccount);
        platformSettingService.updateSetValueByKey(PlatformSettingKey.SMS_PASSWORD, smsPassword);

        //销毁并注入bean
        return Message.ok();
    }


    @PostMapping("/updateEmailSetting")
    @ResponseBody
    public Message<String> updateEmailSetting(String mailAccount, String mailPassword, String smtpServer ,String smtpPort) {
        if (StringUtils.isBlank(mailAccount)){
            return Message.ok("请输入邮箱账号");
        }
        if (StringUtils.isBlank(mailPassword)){
            return Message.ok("请输入邮箱账号密码");
        }
        if (StringUtils.isBlank(smtpServer)){
            return Message.ok("请输入邮箱smtp服务地址");
        }
        if (StringUtils.isBlank(smtpPort)){
            return Message.ok("请输入邮箱smtp端口地址");
        }

        platformSettingService.updateSetValueByKey(PlatformSettingKey.MAIL_ACCOUNT, mailAccount);
        platformSettingService.updateSetValueByKey(PlatformSettingKey.MAIL_PASSWORD, mailPassword);
        platformSettingService.updateSetValueByKey(PlatformSettingKey.SMTP_SERVER, smtpServer);
        platformSettingService.updateSetValueByKey(PlatformSettingKey.SMTP_PORT, smtpPort);

        return Message.ok();
    }
}

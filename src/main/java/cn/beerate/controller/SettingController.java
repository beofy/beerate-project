package cn.beerate.controller;

import cn.beerate.captcha.email.EmailSender;
import cn.beerate.captcha.email.IEmail;
import cn.beerate.captcha.mobile.ISms;
import cn.beerate.captcha.mobile.chuanglan.ChuangLanSms;
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

    private ISms sms;
    private IEmail email;
    private PlatformSettingService platformSettingService;

    @Autowired
    public SettingController(ISms sms, IEmail email, PlatformSettingService platformSettingService) {
        this.sms = sms;
        this.email = email;
        this.platformSettingService = platformSettingService;
    }

    @GetMapping("/setting.html")
    public String platformSetting(Model model) {
        ChuangLanSms chuangLanSms = (ChuangLanSms)sms;
        EmailSender emailSender = (EmailSender)email;
        model.addAttribute(PlatformSettingKey.SMS_ACCOUNT,chuangLanSms.getSmsAccount());
        model.addAttribute(PlatformSettingKey.SMS_PASSWORD,chuangLanSms.getSmsPassword());
        model.addAttribute(PlatformSettingKey.MAIL_ACCOUNT,emailSender.getMailAccount());
        model.addAttribute(PlatformSettingKey.MAIL_PASSWORD,emailSender.getMailPassword());
        model.addAttribute(PlatformSettingKey.SMTP_SERVER,emailSender.getHostName());
        model.addAttribute(PlatformSettingKey.SMTP_PORT,emailSender.getSmtpPort());

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

        ChuangLanSms chuangLanSms = (ChuangLanSms)sms;
        chuangLanSms.setSmsAccount(smsAccount);
        chuangLanSms.setSmsPassword(smsPassword);

        platformSettingService.updateSetValueByKey(PlatformSettingKey.SMS_ACCOUNT, smsAccount);
        platformSettingService.updateSetValueByKey(PlatformSettingKey.SMS_PASSWORD, smsPassword);

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

        EmailSender emailSender = (EmailSender)email;
        emailSender.setHostName(smtpServer);
        emailSender.setMailAccount(mailAccount);
        emailSender.setMailPassword(mailPassword);
        emailSender.setSmtpPort(smtpPort);

        platformSettingService.updateSetValueByKey(PlatformSettingKey.MAIL_ACCOUNT, mailAccount);
        platformSettingService.updateSetValueByKey(PlatformSettingKey.MAIL_PASSWORD, mailPassword);
        platformSettingService.updateSetValueByKey(PlatformSettingKey.SMTP_SERVER, smtpServer);
        platformSettingService.updateSetValueByKey(PlatformSettingKey.SMTP_PORT, smtpPort);

        return Message.ok();
    }


}

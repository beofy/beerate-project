package cn.beerate.captcha.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailSender implements IEmail{

    private final Log logger = LogFactory.getLog(this.getClass());

    private String hostName;
    private String mailAccount;
    private String mailPassword;
    private String smtpPort;

	public EmailSender(String hostName, String mailAccount, String mailPassword, String smtpPort) {
		this.hostName = hostName;
		this.mailAccount = mailAccount;
		this.mailPassword = mailPassword;
		this.smtpPort = smtpPort;
	}

	/**
	 * 发送HMTL格式的邮件
	 *
	 * @param hostName 邮件服务器地址
	 * @param mailAccount 发送邮件的账号
	 * @param mailPassword 发送邮件的密码 
	 * @param toEmail 接收邮件的地址
	 * @param title 邮件的标题
	 * @param content 邮件的内容
	 *
	 */
	private void sendHtmlEmail(String hostName, String mailAccount, String mailPassword, String toEmail, String title, String content){
		try {
			HtmlEmail sendEmail = new HtmlEmail();
			sendEmail.setHostName(hostName);
			sendEmail.setAuthentication(mailAccount, mailPassword);
            logger.info("from email "+mailAccount);
			sendEmail.setFrom(mailAccount);
			sendEmail.setSSLOnConnect(true);
			sendEmail.setSslSmtpPort(this.smtpPort);
			sendEmail.addTo(toEmail);
			
			sendEmail.setSubject(title);
			sendEmail.setCharset("utf-8");
			sendEmail.setHtmlMsg(content);
			sendEmail.send();
		} catch (EmailException e) {
            logger.info("邮件发送失败",e);
		}
	}

    @Override
    public void sendEmail(String toEmail, String title, String content) {
        sendHtmlEmail(hostName,mailAccount, mailPassword, toEmail, title, content);
    }

}

package cn.beerate.captcha.email;

public interface IEmail {

    /**
     * 发送邮件
     * @param toEmail 接收邮件的地址
	 * @param title 邮件的标题
	 * @param content 邮件的内容
     */
    void sendEmail(String toEmail, String title, String content);
}

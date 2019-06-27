package cn.beerate.captcha.mobile.chuanglan;

import cn.beerate.captcha.mobile.ISms;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;

/**
 * 创蓝短信接口
 */
public class ChuangLanSms implements ISms {

    private static final Log logger = LogFactory.getLog(ChuangLanSms.class);

    private String smsAccount;
    private String smsPassword;

    public ChuangLanSms(String smsAccount, String smsPassword) {
        this.smsAccount = smsAccount;
        this.smsPassword = smsPassword;
    }

    /**
     * 发送短信
     * @param smsAccount 账号
     * @param smsPassword 密码
     * @param mobile 手机
     * @param SMScontent 短信内容
     */
    private void sendSMS(String smsAccount,String smsPassword,String mobile,String SMScontent){
        String url = "http://222.73.117.158/msg/";// 应用地址
        boolean needstatus = false;// 是否需要状态报告，需要true，不需要false
        String product = null;// 产品ID
        String extno = null;// 扩展码
        try {
            String code = batchSend(url, smsAccount, smsPassword, mobile, SMScontent, needstatus, product, extno) ;
            logger.info("创蓝短信发送状态："+code);
        } catch (Exception e) {
            logger.info("发送短信失败",e);
        }
    }

    @Override
    public void sendSMS(String mobile, String SMScontent) {
        sendSMS(smsAccount,smsPassword,mobile,SMScontent);
    }

    @Override
    public void sendSMS(List<String> mobiles, String SMScontent) {

    }

    /**
     *
     * @param url 应用地址，类似于http://ip:port/msg/
     * @param account 账号
     * @param pswd 密码
     * @param mobile 手机号码，多个号码使用","分割
     * @param msg 短信内容
     * @param needstatus 是否需要状态报告，需要true，不需要false
     * @return 返回值定义参见HTTP协议文档
     */
    private static String send(String url, String account, String pswd, String mobile, String msg,boolean needstatus, String product, String extno) throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "HttpSendSM", false));
            method.setQueryString(new NameValuePair[] {
                    new NameValuePair("account", account),
                    new NameValuePair("pswd", pswd),
                    new NameValuePair("mobile", mobile),
                    new NameValuePair("needstatus", String.valueOf(needstatus)),
                    new NameValuePair("msg", msg),
                    new NameValuePair("product", product),
                    new NameValuePair("extno", extno),
            });
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }

    }

    /**
     *
     * @param url 应用地址，类似于http://ip:port/msg/
     * @param account 账号
     * @param pswd 密码
     * @param mobile 手机号码，多个号码使用","分割
     * @param msg 短信内容
     * @param needstatus 是否需要状态报告，需要true，不需要false
     * @return 返回值定义参见HTTP协议文档
     */
    private String batchSend(String url, String account, String pswd, String mobile, String msg,boolean needstatus, String product, String extno) throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "HttpBatchSendSM", false));
            method.setQueryString(new NameValuePair[] {
                    new NameValuePair("account", account),
                    new NameValuePair("pswd", pswd),
                    new NameValuePair("mobile", mobile),
                    new NameValuePair("needstatus", String.valueOf(needstatus)),
                    new NameValuePair("msg", msg),
                    new NameValuePair("product", product),
                    new NameValuePair("extno", extno),
            });
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }
    }

    public String getSmsAccount() {
        return smsAccount;
    }

    public void setSmsAccount(String smsAccount) {
        this.smsAccount = smsAccount;
    }

    public String getSmsPassword() {
        return smsPassword;
    }

    public void setSmsPassword(String smsPassword) {
        this.smsPassword = smsPassword;
    }
}

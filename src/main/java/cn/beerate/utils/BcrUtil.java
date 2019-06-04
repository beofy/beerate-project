package cn.beerate.utils;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * 名片自动识别
 */
public class BcrUtil {

	public static String parseImage(String imageBase64){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://dm-57.data.aliyun.com//rest/160601/ocr/ocr_business_card.json");
        httpPost.setHeader("Authorization","APPCODE 6638733a2a8449d49a51db5d00353ffc");
            httpPost.setHeader("Content-Type","application/json; charset=UTF-8");
        String body = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\""+imageBase64+"\"}}]}";
        StringEntity stringEntity= new StringEntity(body,"UTF-8");
        httpPost.setEntity(stringEntity);

        String result = null;
        try {
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpPost);
            HttpEntity entity = closeableHttpResponse.getEntity();

            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
	}

    /**
     * @param imagePath  图片路径
     */
    public static String parse(String imagePath){
        String result = null;
        try {
            result = parseImage(Base64Utils.encodeToString(IOUtils.toByteArray(new URI("file:///"+imagePath))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @param imageFile 图片文件
     */
    public static String parse(File imageFile){
        String result = null;
        try {
            result = parseImage(Base64Utils.encodeToString(IOUtils.toByteArray(new FileInputStream(imageFile))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String parse(InputStream inputStream){
        String result = null;
        try {
            result = parseImage(Base64Utils.encodeToString(IOUtils.toByteArray(inputStream)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}

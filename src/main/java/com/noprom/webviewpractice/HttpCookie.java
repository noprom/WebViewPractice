package com.noprom.webviewpractice;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by noprom.
 * 取出cookie的类
 */
public class HttpCookie extends Thread {
    @Override
    public void run() {

        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://192.168.56.1/index.php");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name","noprom"));
        list.add(new BasicNameValuePair("age","12"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = client.execute(httpPost);

            // 正常返回
            if(response.getStatusLine().getStatusCode()==200){
                AbstractHttpClient absClient = (AbstractHttpClient) client;
                // 取cookie
                List<Cookie> cookies = absClient.getCookieStore().getCookies();

                // 遍历cookies
                for (Cookie cookie :cookies){
                  System.out.print("name="+cookie.getName()+"age="+cookie.getValue());
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package com.noprom.webviewpractice;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by noprom.
 * 下载线程处理类
 */
public class HttpThread extends Thread {
    private String mUrl;

    public HttpThread(String Url) {
        this.mUrl = Url;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始下载！");

            URL url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // 输入流
            InputStream in = connection.getInputStream();
            // 输出流
            FileOutputStream out = null;
            //下载目录
            File downloadFile;
            File sdFile = null;
            // 判断SD卡是否存在
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                // 拿到外置存储目录
                downloadFile = Environment.getExternalStorageDirectory();

                // 具体的目录
                sdFile = new File(downloadFile, "text.apk");
                // 创建输出流
                out = new FileOutputStream(sdFile);
            }

            // 创建缓存
            byte[] b = new byte[6 * 1024];
            int len;
            // 处理输入流
            while ((len = in.read(b)) != -1) {
                if (out != null) {
                    out.write(b, 0, len);
                }
            }

            // 关闭输入输出流
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            System.out.println("下载完成！");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

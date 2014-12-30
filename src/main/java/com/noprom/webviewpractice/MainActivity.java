package com.noprom.webviewpractice;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity {
    // webview
    private WebView mWebView;
    // 后退按钮
    private Button mBackBtn;
    // 刷新按钮
    private Button mRefreshBtn;
    // 标题
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化控件
        mWebView = (WebView) findViewById(R.id.webView);
        mBackBtn = (Button) findViewById(R.id.id_back);
        mRefreshBtn = (Button) findViewById(R.id.id_refresh);
        mTitle = (TextView) findViewById(R.id.id_title);

        mWebView.loadUrl("http://www.baidu.com");
        // 重写方法
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                // 改变标题
                mTitle.setText(title);
                super.onReceivedTitle(view, title);
            }
        });

        // 设置非浏览器显示
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        // 设置监听事件
        mRefreshBtn.setOnClickListener(new MyListener());
        mBackBtn.setOnClickListener(new MyListener());

    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 刷新
                case R.id.id_refresh:
                    mWebView.reload();

                    break;
                // 返回
                case R.id.id_back:
                    finish();
                    break;
                default:
                    break;
            }
        }
    }
}

package com.example.yjw.http;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

public class WebViewActivity extends BaseActivity {
    private WebView webView;
    private WebSettings webSettings;
    private TextView loading,mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initview();
    }

    private void initview(){

        webView =findViewById(R.id.webView1);
        loading =  findViewById(R.id.text_Loading);
        mtitle =  findViewById(R.id.title);



        //WebView加载web资源
        webView.loadUrl("http://www.baidu.com");

        //WebView加载本地资源
        //webView.loadUrl("file:///android_asset/example.html");

        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url  ==null) return false;
                try{
                if (url.startsWith("weixin://")
                        ||url.startsWith("alipay://")
                        ||url.startsWith("tel://")){
                    Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                }catch(Exception e){ //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return false;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }
                //返回值是true的时候是控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器打开
                view.loadUrl(url);
                return true;
                }

            //WebViewClient帮助WebView去处理一些页面控制和请求通知
        });

        //启用支持Javascript
        webSettings =webView.getSettings();
       /* webSettings.setJavaScriptEnabled(true);

        //WebView加载页面优先使用缓存加载
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);*/

        //设置WebChromeClient类,页面加载
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mtitle.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress<100){
                    String progress =newProgress +"%";
                    loading.setText(progress);
                }else if (newProgress ==100){
                    String progress =newProgress +"%";
                    loading.setText(progress);
                }
            }
        });
    }

    //物理按键，点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack(); //返回上一个界面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

}

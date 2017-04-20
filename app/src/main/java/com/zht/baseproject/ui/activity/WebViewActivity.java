package com.zht.baseproject.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zht.baseproject.R;
import com.zht.baseproject.base.BaseActivity;
import com.zht.baseproject.contract.WebViewContract;
import com.zht.baseproject.ui.widget.webview.ImageClickInterface;
import com.zht.baseproject.ui.widget.webview.MyWebChromeClient;
import com.zht.baseproject.ui.widget.webview.MyWebViewClient;

import butterknife.BindView;

/**
 * Created by ZHT on 2017/4/20.
 * 加载网页的Activity
 */

public class WebViewActivity extends BaseActivity implements WebViewContract.View {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initWebView();
    }

    private void initWebView() {
        WebSettings ws = webView.getSettings();
        //网页内容的宽度是否可以大于WebView控件的宽度
        ws.setLoadWithOverviewMode(false);
        //保存表单数据
        ws.setSaveFormData(true);
        //是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);
        //启动应用缓存
        ws.setAppCacheEnabled(true);
        //设置缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        //启用JavaScript执行，默认的是false
        ws.setJavaScriptEnabled(true);
        //页面加载后之后再放开图片
        ws.setBlockNetworkImage(false);
        //使用localStorage则必须打开
        ws.setDomStorageEnabled(true);
        //设置文字的格式
        ws.setDefaultTextEncodingName("UTF-8");

        //WebView5.0开始默认不允许混合模式，https中不能加载http资源，需要设置开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //设置字体默认缩放大小(改变网页字体大小，setTextSize API14被弃用)
        ws.setTextZoom(100);

        webView.setWebChromeClient(new MyWebChromeClient(this));
        webView.setWebViewClient(new MyWebViewClient(this));
        webView.addJavascriptInterface(new ImageClickInterface(this), "injectedObject");
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showLoadSuccess(String msg) {

    }

    @Override
    public void showLoadFail(String msg) {

    }

    @Override
    public void showNoNetwork() {
        showNoNetworkView();
    }

    @Override
    public void addImageClickListener() {
        //遍历img节点，自行添加onclick方法
        //该方法的作用是在图片点击时调用本地的Java接口并传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                //  "objs[i].onclick=function(){alert(this.getAttribute(\"has_link\"));}" +
                "objs[i].onclick=function(){window.injectedObject.imageClick(this.getAttribute(\"src\"),this.getAttribute(\"has_link\"));}" +
                "}" +
                "})()");

        //这个是遍历a节点，并传递节点a的属性，可用于页面跳转
        webView.loadUrl("javascript:(function(){" +
                "var objs =document.getElementsByTagName(\"a\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "objs[i].onclick=function(){" +
                "window.injectedObject.textClick(this.getAttribute(\"type\"),this.getAttribute(\"item_pk\"));}" +
                "}" +
                "})()");
    }
}

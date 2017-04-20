package com.zht.baseproject.ui.widget.webview;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zht.baseproject.R;
import com.zht.baseproject.contract.WebViewContract;
import com.zht.baseproject.ui.activity.WebViewActivity;
import com.zht.baseproject.utils.NetworkUtils;
import com.zht.baseproject.utils.UIUtils;

/**
 * Created by ZHT on 2017/4/20.
 * 在WebViewClient中会监听网页连接
 */

public class MyWebViewClient extends WebViewClient {

    private WebViewContract.View mView;

    private WebViewActivity mActivity;

    public MyWebViewClient(WebViewContract.View view) {
        mView = view;
        mActivity = (WebViewActivity) mView;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //优酷视频跳转到浏览器播放
        if (url.startsWith("http://v.youku.com/")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            mActivity.startActivity(intent);
            return true;

            //电话，短信，邮箱
        } else if (url.startsWith(WebView.SCHEME_TEL) || url.startsWith("sms:") || url.startsWith(WebView.SCHEME_MAILTO)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            mActivity.startActivity(intent);
            return true;
        }
        mView.showLoading(UIUtils.getString(R.string.loading));
        view.loadUrl(url);
        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {

        if (NetworkUtils.isConnected()) {
            mView.addImageClickListener();
            mView.showLoadSuccess(UIUtils.getString(R.string.load_success));
        } else {
            mView.showNoNetwork();
        }

        super.onPageFinished(view, url);
    }

    // 解决视频全屏播放按返回页面被放大的问题
    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
        if (newScale - oldScale > 7) {
            view.setInitialScale((int) (oldScale / newScale * 100)); //异常放大，缩回去。
        }
    }
}

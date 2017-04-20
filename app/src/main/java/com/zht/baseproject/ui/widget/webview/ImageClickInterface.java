package com.zht.baseproject.ui.widget.webview;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import com.zht.baseproject.utils.ToastUtils;

/**
 * Created by ZHT on 2017/4/20.
 * WebView和JS通信的接口
 * 实现图片点击
 */

public class ImageClickInterface {

    private Context context;

    public ImageClickInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void imageClick(String imgUrl, String hasLink) {
        ToastUtils.showLong("点击了图片 url: " + imgUrl);
    }

    @JavascriptInterface
    public void textClick(String type, String item_pk) {
        if (!TextUtils.isEmpty(type) && !TextUtils.isEmpty(item_pk)) {
            ToastUtils.showLong("点击了文字");
        }
    }
}

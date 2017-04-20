package com.zht.baseproject.ui.widget.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.zht.baseproject.R;
import com.zht.baseproject.contract.WebViewContract;
import com.zht.baseproject.ui.activity.WebViewActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ZHT on 2017/4/20.
 * 配置视频播放
 * 配置图片上传
 */

public class MyWebChromeClient extends WebChromeClient {

    private ValueCallback<Uri> mUploadMsg;
    private ValueCallback<Uri[]> mUploadMsgAboveAndroid5;
    public static final int CODE_FILE_CHOOSE = 1;
    public static final int CODE_FILE_CHOOSE_5 = 2;

    private WebViewContract.View mView;
    private WebViewActivity mActivity;
    private View mProgress;
    private View mCustomView;
    private CustomViewCallback mCustomViewCallback;

    public MyWebChromeClient(WebViewContract.View view) {
        mView = view;
        mActivity = (WebViewActivity) mView;
    }

    // 播放网络视频时全屏会被调用的方法
    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 如果一个视图已经存在，那么立刻终止并新建一个
        if (mCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }

        mCustomView = view;
        mCustomViewCallback = callback;
    }

    // 视频播放退出全屏会被调用的
    @Override
    public void onHideCustomView() {
        if (mCustomView == null)// 不是全屏播放状态
            return;
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mCustomView.setVisibility(View.GONE);
        mCustomView = null;
        mCustomViewCallback.onCustomViewHidden();
    }

    // 视频加载时进程loading
    @SuppressLint("InflateParams")
    @Override
    public View getVideoLoadingProgressView() {
        if (mProgress == null) {
            LayoutInflater inflater = LayoutInflater.from(mActivity);
            mProgress = inflater.inflate(R.layout.view_loading, null);
        }
        return mProgress;
    }

    /**
     * 判断是否是全屏
     */
    public boolean inFullScreen() {
        return (mCustomView != null);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        // 设置title
        mActivity.setTitle(title);
    }

    //扩展浏览器上传文件
    //3.0++版本
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        openFileChoose(uploadMsg);
    }

    //3.0--版本
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChoose(uploadMsg);
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChoose(uploadMsg);
    }

    // For Android > 5.0
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, FileChooserParams fileChooserParams) {
        openFileChooseForAndroid5(uploadMsg);
        return true;
    }

    private void openFileChoose(ValueCallback<Uri> uploadMsg) {
        mUploadMsg = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        mActivity.startActivityForResult(Intent.createChooser(i, "文件选择"), CODE_FILE_CHOOSE);
    }

    private void openFileChooseForAndroid5(ValueCallback<Uri[]> uploadMsg) {
        mUploadMsgAboveAndroid5 = uploadMsg;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "图片选择");

        mActivity.startActivityForResult(chooserIntent, CODE_FILE_CHOOSE_5);
    }

    /**
     * 5.0以下 上传图片成功后的回调
     */
    public void uploadMsg(Intent intent, int resultCode) {
        if (null == mUploadMsg)
            return;
        Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
        mUploadMsg.onReceiveValue(result);
        mUploadMsg = null;
    }

    /**
     * 5.0以上 上传图片成功后的回调
     */
    public void uploadMsgAboveAndroid5(Intent intent, int resultCode) {
        if (null == mUploadMsgAboveAndroid5)
            return;
        Uri result = (intent == null || resultCode != RESULT_OK) ? null : intent.getData();
        if (result != null) {
            mUploadMsgAboveAndroid5.onReceiveValue(new Uri[]{result});
        } else {
            mUploadMsgAboveAndroid5.onReceiveValue(new Uri[]{});
        }
        mUploadMsgAboveAndroid5 = null;
    }
}

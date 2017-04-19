package com.zht.baseproject;

import android.app.Application;
import android.os.Handler;

import com.zht.baseproject.utils.LogUtils;

/**
 * Created by ZHT on 2017/4/17.
 * 自定义Application
 */

public class App extends Application {

    private static App mContext;

    private static Handler mMainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        mMainThreadHandler = new Handler();

        //设置是否打印日志
        LogUtils.setIsLog(BuildConfig.LOG_DEBUG);
    }

    public static App getApplication() {
        return mContext;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }
}

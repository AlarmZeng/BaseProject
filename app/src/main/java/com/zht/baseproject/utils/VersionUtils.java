package com.zht.baseproject.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.zht.baseproject.App;

/**
 * Created by ZHT on 2017/6/1.
 */

public class VersionUtils {

    private VersionUtils() {
        throw new IllegalArgumentException("cannot create VersionUtils constructor!");
    }

    private static PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            PackageManager manager = App.getApplication().getPackageManager();
            info = manager.getPackageInfo(App.getApplication().getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return info;
    }

    /**
     * 获取应用版本名
     * @return 版本名
     */
    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    /**
     * 获取应用版本号
     * @return 版本号
     */
    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }
}

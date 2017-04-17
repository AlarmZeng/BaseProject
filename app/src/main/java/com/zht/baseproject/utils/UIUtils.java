package com.zht.baseproject.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.zht.baseproject.App;

/**
 * Created by ZHT on 2017/4/17.
 * 有关UI的工具类，如获取资源(颜色，字符串，drawable等)，
 * 屏幕宽高，dp与px转换
 */

public class UIUtils {

    private static Context getContext() {
        return App.getApplication();
    }

    private static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取颜色值
     * @param resId 颜色资源id
     * @return 颜色值
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getColor(int resId, Resources.Theme theme) {
        return getResources().getColor(resId, theme);
    }

    public static int getColor(String color) {
        return Color.parseColor(color);
    }

    /**
     * 获取Drawable
     * @param resTd Drawable资源id
     * @return Drawable
     */
    public static Drawable getDrawable(int resTd) {
        return getResources().getDrawable(resTd);
    }

    /**
     * 获取字符串
     * @param resId 字符串资源id
     * @return 字符串
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取字符串数组
     * @param resId 数组资源id
     * @return 字符串数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 将dp值转换为px值
     * @param dp 需要转换的dp值
     * @return px值
     */
    public static int dp2px(float dp) {
        return (int) (getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    /**
     * 将px值转换为dp值
     * @param px 需要转换的px值
     * @return dp值
     */
    public static int px2dp(float px) {
        return (int) (px / getResources().getDisplayMetrics().density + 0.5f);
    }

    public static DisplayMetrics getDisplayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics;
    }

    /**
     * 获取屏幕宽度 像素值
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度 像素值
     * @return 屏幕高度
     */
    public static int getScreenHegith() {
        return getDisplayMetrics().heightPixels;
    }
}

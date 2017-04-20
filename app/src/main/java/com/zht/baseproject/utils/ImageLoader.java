package com.zht.baseproject.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zht.baseproject.App;
import com.zht.baseproject.R;
import com.zht.baseproject.ui.widget.CircleImageTransform;

/**
 * Created by ZHT on 2017/4/20.
 * 图片加载
 * Glide的二次封装
 */

public class ImageLoader {

    /**
     * 获取全局(Application)的Context
     * 生命周期为Application的生命周期
     * @return
     */
    private static Context getGlobalContext() {
        return App.getApplication();
    }

    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImage(ImageView imageView, String imageUrl) {
        loadImage(getGlobalContext(), imageView, imageUrl);
    }

    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     * @param context    Context
     * @param imageView  ImageView
     * @param imageUrl   图片地址
     */
    public static void loadImage(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * 加载图片，在加载过程中会显示占位图
     * @param imageView ImageView
     * @param imageUrl 图片地址
     */
    public static void loadImageWithPlaceHolder(ImageView imageView, String imageUrl) {
        loadImageWithPlaceHolder(getGlobalContext(), imageView, imageUrl);
    }

    /**
     * 加载图片，在加载过程中会显示占位图，失败也会显示占位图
     * @param context    Context
     * @param imageView  ImageView
     * @param imageUrl   图片地址
     */
    public static void loadImageWithPlaceHolder(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_default)
                .error(R.drawable.ic_default)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     * @param imageView  ImageView
     * @param imageUrl  图片地址
     */
    public static void loadCircleImage(ImageView imageView, String imageUrl) {
        loadCircleImage(getGlobalContext(), imageView, imageUrl);
    }

    /**
     * 加载圆形图片
     * @param context    Context
     * @param imageView  ImageView
     * @param imageUrl   图片地址
     */
    public static void loadCircleImage(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .transform(new CircleImageTransform(getGlobalContext()))
                .into(imageView);
    }

    /**
     * 加载Gif图
     * @param context    Context
     * @param imageView  ImageView
     * @param gifUrl     gif图地址
     */
    public static void loadGif(Context context, ImageView imageView, String gifUrl) {
        Glide.with(context)
                .load(gifUrl)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * 根据特定的宽高加载图片
     * @param context    Context
     * @param imageView  ImageView
     * @param imageUrl   图片地址
     * @param width      图片的宽度
     * @param height     图片的高度
     */
    public static void loadImageWithSize(Context context, ImageView imageView, String imageUrl, int width, int height) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .override(width, height)
                .into(imageView);
    }

}

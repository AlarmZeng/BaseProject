package com.zht.baseproject.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by ZHT on 2017/6/1.
 * 调用相机工具类
 */

public class CameraUtils {

    public static Uri takePhotoUri = null;

    public static final int CODE_TAKE_PHOTO = 0;

    public static final int CODE_TAKE_PHOTO_ZOOM = 1;

    /**
     * 调用相机拍照
     * @param activity 调用的Activity
     */
    public static void takePhoto(Activity activity) {

        String pictureName = System.currentTimeMillis() + ".jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), pictureName);
        Uri uri = Uri.fromFile(file);
        takePhotoUri = uri;
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, CODE_TAKE_PHOTO);
    }

    public static void takePhotoZoom(Activity activity) {
        if (null != takePhotoUri) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(takePhotoUri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, takePhotoUri);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            activity.startActivityForResult(intent, CODE_TAKE_PHOTO_ZOOM);
        }
    }
}

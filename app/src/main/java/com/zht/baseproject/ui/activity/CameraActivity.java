package com.zht.baseproject.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zht.baseproject.R;
import com.zht.baseproject.base.BaseActivity;
import com.zht.baseproject.utils.CameraUtils;
import com.zht.baseproject.utils.ImageLoader;
import com.zht.baseproject.utils.PermissionListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ZHT on 2017/6/2.
 * 相机，相册调用Activity
 */

public class CameraActivity extends BaseActivity {

    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.bt_camera)
    Button bt_camera;
    @BindView(R.id.bt_album)
    Button bt_album;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    @OnClick({R.id.bt_camera, R.id.bt_album})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.bt_camera:

                requestPermissions(new String[]{ Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE }, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        CameraUtils.takePhoto(CameraActivity.this);
                    }

                    @Override
                    public void onDenied(List<String> deniedPermissions) {

                    }
                });

                break;

            case R.id.bt_album:
                CameraUtils.albumChoose(CameraActivity.this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case CameraUtils.CODE_TAKE_PHOTO :
                    CameraUtils.takePhotoZoom(CameraActivity.this);
                    break;

                case CameraUtils.CODE_TAKE_PHOTO_ZOOM :

                    if (null != CameraUtils.takePhotoUri) {
                        ImageLoader.loadImageWithUri(CameraActivity.this, iv_photo, CameraUtils.takePhotoUri);
                    }

                    break;

                case CameraUtils.CODE_ALBUM_CHOOSE :

                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4以上系统使用这个方法处理图片
                        CameraUtils.handleImageOnKitKat(CameraActivity.this, data);
                    } else {
                        //4.4一下系统使用这个方法处理图片
                        CameraUtils.handleImageBeforeKitKat(CameraActivity.this, data);
                    }

                    break;

                case CameraUtils.CODE_ALBUM_CHOOSE_ZOOM :

                    if (null != CameraUtils.albumPhotonUri) {
                        ImageLoader.loadImageWithUri(CameraActivity.this, iv_photo, CameraUtils.albumPhotonUri);
                    }

                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

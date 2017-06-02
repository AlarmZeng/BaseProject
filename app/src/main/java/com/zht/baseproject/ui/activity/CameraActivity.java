package com.zht.baseproject.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zht.baseproject.R;
import com.zht.baseproject.base.BaseActivity;

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
                
                break;

            case R.id.bt_album:
                break;
        }
    }
}

package com.zht.baseproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zht.baseproject.R;
import com.zht.baseproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.bt_loading)
    Button bt_loading;
    @BindView(R.id.bt_success)
    Button bt_success;
    @BindView(R.id.bt_fail)
    Button bt_fail;
    @BindView(R.id.bt_camera_activity)
    Button bt_cameraActivity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

        /*HttpFactory.getHttpApiSingleton()
                .getCategoryData("Android", 10, 1)
                .compose(new HttpTransformer<HttpResult<List<GankEntity>>, List<GankEntity>>())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoadingView();
                    }
                })
                .subscribe(new Subscriber<List<GankEntity>>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.d(TAG, "Completed");
                        showContentView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "OnError, Error is " + e.toString());
                        showErrorView();
                    }

                    @Override
                    public void onNext(List<GankEntity> gankEntities) {

                    }
                });*/

    }

    @OnClick({R.id.bt_loading, R.id.bt_success, R.id.bt_fail, R.id.bt_camera_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.bt_loading:
                showProgressDialog();
                break;

            case R.id.bt_success:
                showProgressSuccess("加载成功");
                break;

            case R.id.bt_fail:
                showProgressFail("加载失败");
                break;

            case R.id.bt_camera_activity:
                startActivity(new Intent(this, CameraActivity.class));
                break;
        }
    }
}

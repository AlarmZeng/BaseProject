package com.zht.baseproject.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zht.baseproject.R;
import com.zht.baseproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_loading)
    Button bt_loading;
    @BindView(R.id.bt_success)
    Button bt_success;
    @BindView(R.id.bt_fail)
    Button bt_fail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    @OnClick({R.id.bt_loading, R.id.bt_success, R.id.bt_fail})
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
        }
    }
}

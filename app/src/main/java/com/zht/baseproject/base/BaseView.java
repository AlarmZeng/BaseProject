package com.zht.baseproject.base;

/**
 * Created by ZHT on 2017/4/18.
 * View的基本接口，可以定义View中共有的方法
 */

public interface BaseView {

    void showLoading(String msg);

    void showLoadSuccess(String msg);

    void showLoadFail(String msg);
}

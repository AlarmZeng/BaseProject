package com.zht.baseproject.base;

/**
 * Created by ZHT on 2017/4/18.
 * Presenter的基本接口，定义Presenter共有的方法
 */

public interface BasePresenter {

    void attachView(BaseView view);

    void detachView();
}

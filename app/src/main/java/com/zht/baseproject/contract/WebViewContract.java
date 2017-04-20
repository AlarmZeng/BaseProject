package com.zht.baseproject.contract;

import com.zht.baseproject.base.BasePresenter;
import com.zht.baseproject.base.BaseView;

/**
 * Created by ZHT on 2017/4/20.
 */

public interface WebViewContract {

    interface View extends BaseView {

        void showNoNetwork();

        void addImageClickListener();
    }

    interface Presenter extends BasePresenter {

    }
}

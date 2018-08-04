package com.cjcm.housekeeping.mvp.contract;

import com.cjcm.housekeeping.base.BaseModel;
import com.cjcm.housekeeping.base.BasePresenter;
import com.cjcm.housekeeping.base.BaseView;
import com.cjcm.housekeeping.bean.AboutUsBean;
import com.cjcm.housekeeping.net.ApiException;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public interface AboutContract {
    interface View extends BaseView {
        void onAboutSuccess(AboutUsBean bean);

        void onAboutError(ApiException.ResponseException e);
    }

    interface Model extends BaseModel {
        Observable<AboutUsBean> aboutUs();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void aboutUs();
    }
}

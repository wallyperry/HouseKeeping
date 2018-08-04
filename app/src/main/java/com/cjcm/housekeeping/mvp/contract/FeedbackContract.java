package com.cjcm.housekeeping.mvp.contract;

import com.cjcm.housekeeping.base.BaseModel;
import com.cjcm.housekeeping.base.BasePresenter;
import com.cjcm.housekeeping.base.BaseView;
import com.cjcm.housekeeping.bean.FeedbackBean;
import com.cjcm.housekeeping.net.ApiException;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public interface FeedbackContract {
    interface View extends BaseView {
        void onFeedbackSuccess(FeedbackBean bean);

        void onFeedbackError(ApiException.ResponseException e);
    }

    interface Model extends BaseModel {
        Observable<FeedbackBean> feedback(String phone, String msg);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void feedback(String phone, String msg);
    }
}

package com.cjcm.housekeeping.mvp.contract;

import com.cjcm.housekeeping.base.BaseModel;
import com.cjcm.housekeeping.base.BasePresenter;
import com.cjcm.housekeeping.base.BaseView;
import com.cjcm.housekeeping.bean.SaveOrderBean;
import com.cjcm.housekeeping.net.ApiException;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/28
 */
public interface SaveOrderContract {
    interface View extends BaseView {
        void onSaveSuccess(SaveOrderBean bean);

        void onSaveError(ApiException.ResponseException e);
    }

    interface Model extends BaseModel {
        Observable<SaveOrderBean> saveOrder(String content, String phone, String address, String time, int status, String remarks);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void saveOrder(String content, String phone, String address, String time, int status, String remarks);
    }
}

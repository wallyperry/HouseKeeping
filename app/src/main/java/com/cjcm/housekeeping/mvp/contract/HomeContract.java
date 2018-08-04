package com.cjcm.housekeeping.mvp.contract;


import com.cjcm.housekeeping.base.BaseModel;
import com.cjcm.housekeeping.base.BasePresenter;
import com.cjcm.housekeeping.base.BaseView;
import com.cjcm.housekeeping.bean.HomeBean;
import com.cjcm.housekeeping.net.ApiException;

import rx.Observable;

/**
 * ren.perry.basemvpframe.mvp.contract
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

public interface HomeContract {
    interface View extends BaseView {
        void onSuccess(HomeBean bean);

        void onError(ApiException.ResponseException e);
    }

    interface Model extends BaseModel {
        Observable<HomeBean> getHomeData();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHomeData();
    }
}

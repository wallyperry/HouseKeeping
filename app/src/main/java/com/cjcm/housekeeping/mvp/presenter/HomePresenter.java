package com.cjcm.housekeeping.mvp.presenter;


import com.cjcm.housekeeping.bean.HomeBean;
import com.cjcm.housekeeping.mvp.contract.HomeContract;
import com.cjcm.housekeeping.mvp.model.HomeModel;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.rx.BaseSubscriber;

/**
 * ren.perry.basemvpframe.mvp
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

public class HomePresenter extends HomeContract.Presenter {

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        addSubscribe(mModel.getHomeData()
                .subscribe(new BaseSubscriber<HomeBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        mView.onError(e);
                    }

                    @Override
                    public void onNext(HomeBean bean) {
                        mView.onSuccess(bean);
                    }
                }));
    }
}

package com.cjcm.housekeeping.mvp.presenter;

import com.cjcm.housekeeping.bean.AboutUsBean;
import com.cjcm.housekeeping.mvp.contract.AboutContract;
import com.cjcm.housekeeping.mvp.model.AboutModel;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.rx.BaseSubscriber;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class AboutPresenter extends AboutContract.Presenter {

    public AboutPresenter(AboutContract.View view) {
        mView = view;
        mModel = new AboutModel();
    }

    @Override
    public void aboutUs() {
        addSubscribe(mModel.aboutUs()
                .subscribe(new BaseSubscriber<AboutUsBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        mView.onAboutError(e);
                    }

                    @Override
                    public void onNext(AboutUsBean bean) {
                        mView.onAboutSuccess(bean);
                    }
                }));
    }
}

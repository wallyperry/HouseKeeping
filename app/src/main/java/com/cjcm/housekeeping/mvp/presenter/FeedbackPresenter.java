package com.cjcm.housekeeping.mvp.presenter;

import com.cjcm.housekeeping.bean.FeedbackBean;
import com.cjcm.housekeeping.mvp.contract.FeedbackContract;
import com.cjcm.housekeeping.mvp.model.FeedbackModel;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.rx.BaseSubscriber;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class FeedbackPresenter extends FeedbackContract.Presenter {

    public FeedbackPresenter(FeedbackContract.View view) {
        mView = view;
        mModel = new FeedbackModel();
    }

    @Override
    public void feedback(String phone, String msg) {
        addSubscribe(mModel.feedback(phone, msg)
                .subscribe(new BaseSubscriber<FeedbackBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        mView.onFeedbackError(e);
                    }

                    @Override
                    public void onNext(FeedbackBean bean) {
                        mView.onFeedbackSuccess(bean);
                    }
                }));

    }
}

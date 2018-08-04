package com.cjcm.housekeeping.mvp.presenter;

import com.cjcm.housekeeping.bean.SaveOrderBean;
import com.cjcm.housekeeping.mvp.contract.SaveOrderContract;
import com.cjcm.housekeeping.mvp.model.SaveOrderModel;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.rx.BaseSubscriber;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/28
 */
public class SaveOrderPresenter extends SaveOrderContract.Presenter {
    public SaveOrderPresenter(SaveOrderContract.View view) {
        mView = view;
        mModel = new SaveOrderModel();
    }

    @Override
    public void saveOrder(String content, String phone, String address, String time, int status, String remarks) {
        addSubscribe(mModel.saveOrder(content, phone, address, time, status, remarks)
                .subscribe(new BaseSubscriber<SaveOrderBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        mView.onSaveError(e);
                    }

                    @Override
                    public void onNext(SaveOrderBean bean) {
                        mView.onSaveSuccess(bean);
                    }
                }));
    }
}

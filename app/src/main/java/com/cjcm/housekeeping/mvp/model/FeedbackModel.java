package com.cjcm.housekeeping.mvp.model;

import com.cjcm.housekeeping.api.ApiEngine;
import com.cjcm.housekeeping.bean.FeedbackBean;
import com.cjcm.housekeeping.mvp.contract.FeedbackContract;
import com.cjcm.housekeeping.rx.RxSchedulers;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class FeedbackModel implements FeedbackContract.Model {
    @Override
    public Observable<FeedbackBean> feedback(String phone, String msg) {
        return ApiEngine.getInstance()
                .getBaseService()
                .feedback(phone, msg)
                .compose(RxSchedulers.switchThread());
    }
}

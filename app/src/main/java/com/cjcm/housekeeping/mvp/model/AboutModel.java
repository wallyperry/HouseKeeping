package com.cjcm.housekeeping.mvp.model;

import com.cjcm.housekeeping.api.ApiEngine;
import com.cjcm.housekeeping.bean.AboutUsBean;
import com.cjcm.housekeeping.mvp.contract.AboutContract;
import com.cjcm.housekeeping.rx.RxSchedulers;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class AboutModel implements AboutContract.Model {
    @Override
    public Observable<AboutUsBean> aboutUs() {
        return ApiEngine.getInstance()
                .getBaseService()
                .aboutUs()
                .compose(RxSchedulers.switchThread());
    }
}

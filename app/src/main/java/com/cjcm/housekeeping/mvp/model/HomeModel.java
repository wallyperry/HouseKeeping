package com.cjcm.housekeeping.mvp.model;


import com.cjcm.housekeeping.api.ApiEngine;
import com.cjcm.housekeeping.bean.HomeBean;
import com.cjcm.housekeeping.mvp.contract.HomeContract;
import com.cjcm.housekeeping.rx.RxSchedulers;

import rx.Observable;

/**
 * ren.perry.basemvpframe.mvp.model
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<HomeBean> getHomeData() {
        return ApiEngine.getInstance()
                .getBaseService()
                .homeData()
                .compose(RxSchedulers.switchThread());
    }
}

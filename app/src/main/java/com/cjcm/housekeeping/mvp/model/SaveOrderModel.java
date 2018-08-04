package com.cjcm.housekeeping.mvp.model;

import com.cjcm.housekeeping.api.ApiEngine;
import com.cjcm.housekeeping.bean.SaveOrderBean;
import com.cjcm.housekeeping.mvp.contract.SaveOrderContract;
import com.cjcm.housekeeping.rx.RxSchedulers;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/28
 */
public class SaveOrderModel implements SaveOrderContract.Model {
    @Override
    public Observable<SaveOrderBean> saveOrder(String content, String phone, String address, String time, int status, String remarks) {
        return ApiEngine.getInstance()
                .getBaseService()
                .saveOrder(content, phone, address, time, status, remarks)
                .compose(RxSchedulers.switchThread());
    }
}

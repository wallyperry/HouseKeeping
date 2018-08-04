package com.cjcm.housekeeping.mvp.model;

import com.cjcm.housekeeping.api.ApiEngine;
import com.cjcm.housekeeping.bean.ProductBean;
import com.cjcm.housekeeping.mvp.contract.ProductContract;
import com.cjcm.housekeeping.rx.RxSchedulers;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class ProductModel implements ProductContract.Model {
    @Override
    public Observable<ProductBean> productData(int page, int size, String type) {
        return ApiEngine.getInstance()
                .getBaseService()
                .productList(page, size, type)
                .compose(RxSchedulers.switchThread());
    }
}

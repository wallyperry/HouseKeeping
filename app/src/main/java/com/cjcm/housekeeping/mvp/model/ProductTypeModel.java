package com.cjcm.housekeeping.mvp.model;

import com.cjcm.housekeeping.api.ApiEngine;
import com.cjcm.housekeeping.bean.ProductTypeBean;
import com.cjcm.housekeeping.mvp.contract.ProductTypeConstract;
import com.cjcm.housekeeping.rx.RxSchedulers;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/7/23
 */
public class ProductTypeModel implements ProductTypeConstract.Model {
    @Override
    public Observable<ProductTypeBean> productType(String parentId) {
        return ApiEngine.getInstance()
                .getBaseService()
                .productType(parentId)
                .compose(RxSchedulers.switchThread());
    }
}

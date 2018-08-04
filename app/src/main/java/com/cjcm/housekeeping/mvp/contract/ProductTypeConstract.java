package com.cjcm.housekeeping.mvp.contract;

import com.cjcm.housekeeping.base.BaseModel;
import com.cjcm.housekeeping.base.BasePresenter;
import com.cjcm.housekeeping.base.BaseView;
import com.cjcm.housekeeping.bean.ProductTypeBean;
import com.cjcm.housekeeping.net.ApiException;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/7/23
 */
public interface ProductTypeConstract {

    interface View extends BaseView {
        void onProductTypeSuccess(ProductTypeBean bean);

        void onProductTypeError(ApiException.ResponseException e);
    }

    interface Model extends BaseModel {
        Observable<ProductTypeBean> productType(String parentId);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void productType(String parentId);
    }
}

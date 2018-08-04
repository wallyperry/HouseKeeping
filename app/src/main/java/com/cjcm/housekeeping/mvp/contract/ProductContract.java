package com.cjcm.housekeeping.mvp.contract;

import com.cjcm.housekeeping.base.BaseModel;
import com.cjcm.housekeeping.base.BasePresenter;
import com.cjcm.housekeeping.base.BaseView;
import com.cjcm.housekeeping.bean.ProductBean;
import com.cjcm.housekeeping.net.ApiException;

import rx.Observable;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public interface ProductContract {
    interface View extends BaseView {
        void onProductSuccess(ProductBean bean);

        void onProductError(ApiException.ResponseException e);
    }

    interface Model extends BaseModel {
        Observable<ProductBean> productData(int page, int size, String type);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void productData(int page, int size, String type);
    }
}

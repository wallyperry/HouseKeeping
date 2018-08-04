package com.cjcm.housekeeping.mvp.presenter;

import com.cjcm.housekeeping.bean.ProductBean;
import com.cjcm.housekeeping.mvp.contract.ProductContract;
import com.cjcm.housekeeping.mvp.model.ProductModel;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.rx.BaseSubscriber;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class ProductPresenter extends ProductContract.Presenter {
    public ProductPresenter(ProductContract.View view) {
        mView = view;
        mModel = new ProductModel();
    }

    @Override
    public void productData(int page, int size, String type) {
        addSubscribe(mModel.productData(page, size, type)
                .subscribe(new BaseSubscriber<ProductBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        mView.onProductError(e);
                    }

                    @Override
                    public void onNext(ProductBean bean) {
                        mView.onProductSuccess(bean);
                    }
                }));
    }
}

package com.cjcm.housekeeping.mvp.presenter;

import android.content.Context;

import com.cjcm.housekeeping.bean.ProductTypeBean;
import com.cjcm.housekeeping.mvp.contract.ProductTypeConstract;
import com.cjcm.housekeeping.mvp.model.ProductTypeModel;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.rx.BaseSubscriber;

import ren.perry.perry.LoadingDialog;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/7/23
 */
public class ProductTypePresenter extends ProductTypeConstract.Presenter {
    private LoadingDialog dialog;

    public ProductTypePresenter(ProductTypeConstract.View view, Context context) {
        mView = view;
        mModel = new ProductTypeModel();
        dialog = new LoadingDialog(context, "请稍后");
        dialog.setNotCancel();
    }

    @Override
    public void productType(String parentId) {
        mModel.productType(parentId)
                .doOnSubscribe(() -> dialog.show())
                .doOnCompleted(() -> dialog.dismiss())
                .subscribe(new BaseSubscriber<ProductTypeBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        dialog.dismiss();
                        mView.onProductTypeError(e);
                    }

                    @Override
                    public void onNext(ProductTypeBean bean) {
                        mView.onProductTypeSuccess(bean);
                    }
                });

    }
}

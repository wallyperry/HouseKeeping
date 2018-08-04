package com.cjcm.housekeeping.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/13
 */
public class BasePresenter<V extends BaseView, M extends BaseModel> {

    protected V mView;
    protected M mModel;

    protected CompositeSubscription mCompositeSubscription;

    protected void addSubscribe(Subscription subscription){
        if (mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void unSubscribe() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }
}

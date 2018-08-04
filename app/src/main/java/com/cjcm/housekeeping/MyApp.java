package com.cjcm.housekeeping;

import android.app.Application;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/20
 */
public class MyApp extends Application {

    private static MyApp mContext;

    public static MyApp getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mContext == null) {
            mContext = this;
        }
    }
}

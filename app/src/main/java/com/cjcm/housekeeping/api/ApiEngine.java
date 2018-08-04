package com.cjcm.housekeeping.api;


import com.cjcm.housekeeping.Constants;
import com.cjcm.housekeeping.net.NetClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author perry
 */
public class ApiEngine {
    private volatile static ApiEngine apiEngine;

    private ApiEngine() {
    }

    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    public ApiService getBaseService() {
        return new Retrofit.Builder()
                .baseUrl(Constants.Api.BASE_URL)
                .client(NetClient.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(ApiService.class);
    }
}
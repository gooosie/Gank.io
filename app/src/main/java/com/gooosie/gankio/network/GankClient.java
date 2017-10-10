package com.gooosie.gankio.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * GankClient
 */

public class GankClient {
    public static final String HOST = "http://gank.io/api/";
    private static GankApi mGankApi;
    protected static final Object monitor = new Object();

    private static Retrofit retrofit;

    private GankClient() {

    }

    static {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public static GankApi getGankRetrofitInstance() {
        synchronized (monitor) {
            if (mGankApi == null) {
                mGankApi = retrofit.create(GankApi.class);
            }
            return mGankApi;
        }
    }
}

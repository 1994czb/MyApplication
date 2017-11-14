package com.example.mvpdemo2.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/9.
 */

public class RetrofitManmge {

    private Retrofit mRetrofit;
    private static final String DEFUALT_BASE_URL = "http://172.17.29.27/quarter/";

    public static class SingleHolder{
        private static final RetrofitManmge _instance=new RetrofitManmge(DEFUALT_BASE_URL);
    }

    public static RetrofitManmge getDefault(){
        return SingleHolder._instance;
    }

    public static RetrofitManmge getRetrofitManager(String baseaUrl){
        return new RetrofitManmge(baseaUrl);
    }



    public RetrofitManmge(String baseUrl) {
        this.mRetrofit = buildRetrofit(baseUrl);
    }

    private Retrofit buildRetrofit(String baseUrl) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();
    }

    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }

}

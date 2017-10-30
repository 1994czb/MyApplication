package com.example.mvpdemo3.modle.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/9.
 */

public class RetrofitManager {

    private Retrofit mRetrofit;
    private static final String DEFUALT_BASE_URL="http://www.baidu.com";

    private static class SingleHolder{
        private static final RetrofitManager _instance = new RetrofitManager(DEFUALT_BASE_URL);
    }

    public static RetrofitManager getDefualt(){
        return SingleHolder._instance;
    }

    public static RetrofitManager getRetrofitManager(String baseUrl){
        return new RetrofitManager(baseUrl);
    }

    public RetrofitManager(String baseUrl) {
        this.mRetrofit = buildRetrofit(baseUrl);
    }

    private Retrofit buildRetrofit(String baseUrl) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder.build();
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}

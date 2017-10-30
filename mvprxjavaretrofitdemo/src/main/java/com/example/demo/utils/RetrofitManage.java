package com.example.demo.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/8.
 */

public class RetrofitManage {
    private Retrofit mRetrofit;
    private static final String DEFUALT_BASE_URL = "http://www.baidu.com";

    //一种单例模式
    public static class SingleHolder {
        private static final RetrofitManage _instance = new RetrofitManage(DEFUALT_BASE_URL);
    }

    //使用默认的单例RetrofitManager，是默认的baseurl
    public static RetrofitManage getDefault() {
        return SingleHolder._instance;
    }

    //如果baseurl不是默认的话，那就调用这个方法构造一个新的RetrofitManager
    public static RetrofitManage getRetrofitManager(String baseUrl) {
        return new RetrofitManage(baseUrl);
    }

    public RetrofitManage(String baseUrl) {
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

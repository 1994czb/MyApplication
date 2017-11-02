package com.example.mvprxjavaretrofitdemo5.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/2.
 */

public class RegisterManager {

    private Retrofit mRetrofit;
    private static final String DEFUALT_BASE_URL = "http://www.baidu.com";

    //一种单例模式
    private static class SingleHolder {
        private static final RegisterManager _instance = new RegisterManager(DEFUALT_BASE_URL);
    }

    //使用默认的单例RetrofitManager，是默认的baseurl
    public static RegisterManager getDefault() {
        return SingleHolder._instance;
    }

    //如果baseurl不是默认的话，那就调用这个方法构造一个新的RetrofitManager
    public static RegisterManager getRetrofitManager(String baseUrl) {
        return new RegisterManager(baseUrl);
    }


    public RegisterManager(String baseUrl) {

        this.mRetrofit = buildRetrofit(baseUrl);

    }

    //封装retrofit的方法
    private Retrofit buildRetrofit(String baseUrl) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}

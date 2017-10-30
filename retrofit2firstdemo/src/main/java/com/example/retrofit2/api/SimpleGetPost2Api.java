package com.example.retrofit2.api;

import com.example.retrofit2.bean.NewsBean2;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/10/8.
 */

public interface SimpleGetPost2Api {
    //GET请求方式
//    @GET("get/")
//    Call<NewsBean2> getData(@QueryMap HashMap<String,String> map);

    //post请求方式
    @FormUrlEncoded
    @POST("get/")
    Call<NewsBean2> getData(@FieldMap HashMap<String,String> map);

}

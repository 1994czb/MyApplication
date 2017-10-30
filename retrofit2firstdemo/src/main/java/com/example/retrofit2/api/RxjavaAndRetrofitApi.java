package com.example.retrofit2.api;


import com.example.retrofit2.bean.NewsBean2;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/10/8.
 */

public interface RxjavaAndRetrofitApi {
    @FormUrlEncoded
    @POST("get/")
        //Call<NewsBean2> getData(@FieldMap HashMap<String, String> map);

    Observable<NewsBean2> getData(@FieldMap HashMap<String, String> map);

}

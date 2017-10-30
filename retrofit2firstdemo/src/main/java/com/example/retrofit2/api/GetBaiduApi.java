package com.example.retrofit2.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/10/8.
 */

public interface GetBaiduApi {
    @GET(".")
    Call<String> getBaidu();
}

package com.example.retrofit2.api;

import com.example.retrofit2.bean.NewsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/10/8.
 */

public interface SimpleGetApi {
    @GET("content/{number}/{page}")
    Call<NewsBean> getData(@Path("number") int n,@Path("page") int p);
}

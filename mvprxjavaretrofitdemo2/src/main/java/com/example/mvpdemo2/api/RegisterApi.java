package com.example.mvpdemo2.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface RegisterApi {
//    @GET("user/selectUserAll/")
//    Observable<ResponseBody> register(@Path("Id") int id);

    @FormUrlEncoded
    @POST("user/selectUserAll")
    Observable<ResponseBody> register(@Field("Id") int id);
}

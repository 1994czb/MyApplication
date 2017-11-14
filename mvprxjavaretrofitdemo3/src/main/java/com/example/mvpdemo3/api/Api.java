package com.example.mvpdemo3.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface Api {
    @FormUrlEncoded
    @POST("user/addLogin")
    Observable<ResponseBody> register(@Field("userPassword")
                                              String userPassword, @Field("userPhone") String userPhone);

}

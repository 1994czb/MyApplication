package com.example.demo.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/10/8.
 */
//// // TODO: 2017/10/10 Retrofit的一个Api，用来拼接请求操作的接口
public interface IRegisterApi {
    @GET("media/showMedia")
    Observable<ResponseBody> register();
}

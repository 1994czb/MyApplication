package com.example.mvpdemo2.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface RegisterApi {
    @GET("https://www.baidu.com")
    Observable<ResponseBody> register();
}

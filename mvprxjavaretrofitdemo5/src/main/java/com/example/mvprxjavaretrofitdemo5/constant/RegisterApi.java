package com.example.mvprxjavaretrofitdemo5.constant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/11/2.
 */

public interface RegisterApi {

    @GET("http://www.baidu.com")
    Observable<ResponseBody> register();

}

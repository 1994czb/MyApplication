package com.example.mvpdemo3.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface Api {
    @GET("http://www.baidu.com")
    Observable<ResponseBody> register();

}

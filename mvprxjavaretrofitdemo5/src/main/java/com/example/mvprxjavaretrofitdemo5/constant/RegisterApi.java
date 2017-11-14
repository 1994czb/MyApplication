package com.example.mvprxjavaretrofitdemo5.constant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/11/2.
 */

public interface RegisterApi {

    //    请求地址：http://apis.juhe.cn/catering/query
//    请求参数：lng=121.538123&lat=31.677132&radius=&page=&dtype=&key=28bd9c1ae0c57280cc432ad77d93c045
//    请求方式：POST


    //模拟接口  http://apis.juhe.cn/catering/query
    //http://apis.juhe.cn/   假装为BaseUrl  放在RequestManager   请求参数是key
    @FormUrlEncoded
    @POST("catering/query")
    Observable<ResponseBody> register(@Field("lng") String lng, @Field("lat") String lat, @Field("key") String key);

}

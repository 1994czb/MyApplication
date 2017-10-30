package com.example.retrofit2.api;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/10/8.
 */

public interface DownFileApi {
    @FormUrlEncoded
    @POST("195D0D")
    Call<ResponseBody> doweFile(@FieldMap HashMap<String,String> map);
}

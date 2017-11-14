package com.example.mvprxjavaretrofitdemo5.modle;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/2.
 */

public interface IRegisterModle {

    //括号里面写的是请求参数
    Observable<ResponseBody> register(String lng,String lat,String key);

}

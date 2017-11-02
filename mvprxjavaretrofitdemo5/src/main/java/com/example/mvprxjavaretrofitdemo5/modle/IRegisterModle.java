package com.example.mvprxjavaretrofitdemo5.modle;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/2.
 */

public interface IRegisterModle {

    Observable<ResponseBody> register();

}

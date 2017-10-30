package com.example.demo.modle;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/10/8.
 */

public interface IRegisterModle {
    Observable<ResponseBody> register();
}

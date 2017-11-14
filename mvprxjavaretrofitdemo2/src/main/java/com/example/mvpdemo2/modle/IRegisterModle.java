package com.example.mvpdemo2.modle;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface IRegisterModle {
    Observable<ResponseBody> register(int id);
}

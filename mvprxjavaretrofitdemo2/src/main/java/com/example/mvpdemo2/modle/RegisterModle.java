package com.example.mvpdemo2.modle;

import com.example.mvpdemo2.api.RegisterApi;
import com.example.mvpdemo2.utils.RetrofitManmge;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/10/9.
 */

public class RegisterModle implements IRegisterModle {
    @Override
    public Observable<ResponseBody> register() {
        return RetrofitManmge.getDefault().create(RegisterApi.class).register();
    }
}

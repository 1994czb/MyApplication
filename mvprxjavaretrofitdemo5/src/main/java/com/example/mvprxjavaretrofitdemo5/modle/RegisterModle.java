package com.example.mvprxjavaretrofitdemo5.modle;

import com.example.mvprxjavaretrofitdemo5.constant.RegisterApi;
import com.example.mvprxjavaretrofitdemo5.utils.RegisterManager;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/2.
 */

public class RegisterModle implements IRegisterModle {
    @Override
    public Observable<ResponseBody> register() {
        return RegisterManager.getDefault().create(RegisterApi.class).register();
    }
}

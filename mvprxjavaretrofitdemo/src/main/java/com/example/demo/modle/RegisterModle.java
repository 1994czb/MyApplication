package com.example.demo.modle;

import com.example.demo.api.IRegisterApi;
import com.example.demo.utils.RetrofitManage;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/10/8.
 */

public class RegisterModle implements IRegisterModle {
    @Override
    public Observable<ResponseBody> register() {
        return RetrofitManage.getDefault().create(IRegisterApi.class).register();
    }
}

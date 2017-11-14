package com.example.mvpdemo3.modle;

import com.example.mvpdemo3.api.Api;
import com.example.mvpdemo3.modle.utils.RetrofitManager;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/10/9.
 */

public class RegisterModle implements IRegisterModle {

    @Override
    public Observable<ResponseBody> register(String userPassword, String userPhone) {
        return RetrofitManager.getDefualt().create(Api.class).register(userPassword,userPhone);
    }
}

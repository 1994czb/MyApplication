package com.example.mvpdemo3.presenter;

import android.util.Log;

import com.example.mvpdemo3.modle.RegisterModle;
import com.example.mvpdemo3.view.IRegisterView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/10/9.
 */

public class RegisterPresenter extends IRegisterPresenter<IRegisterView>{

    private static final String TAG = "TAG";
    private RegisterModle mRegisterModle;

    public RegisterPresenter(IRegisterView view) {
        super(view);
    }

    @Override
    protected void init() {
        super.init();
        mRegisterModle = new RegisterModle();
    }

    public void register(String userPassword, String userPhone){
        Observable<ResponseBody> responseBodyObservable = mRegisterModle.register(userPassword,userPhone);
        responseBodyObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Log.e(TAG, "accept: "+responseBody);
                        if (view!=null){
                            view.registerSucced();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: "+throwable );
                        if (view!=null){
                            view.registerFaild();
                        }
                    }
                });
    }

}

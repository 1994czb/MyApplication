package com.example.mvprxjavaretrofitdemo5.presenter;

import com.example.mvprxjavaretrofitdemo5.modle.RegisterModle;
import com.example.mvprxjavaretrofitdemo5.view.IRegisterView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/2.
 */

public class RegisterPresenter extends IRegisterPresenter<IRegisterView> {


    private RegisterModle mRegisterModle;

    public RegisterPresenter(IRegisterView view) {
        super(view);
    }

    @Override
    protected void init() {

        mRegisterModle = new RegisterModle();

    }

    public void register(String lng, String lat, String key) {
        Observable<ResponseBody> registerObservable = mRegisterModle.register(lng,lat,key);
        registerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        if (view != null) {
                            view.succee(responseBody.string());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.faild();
                        }
                    }
                });
    }


}

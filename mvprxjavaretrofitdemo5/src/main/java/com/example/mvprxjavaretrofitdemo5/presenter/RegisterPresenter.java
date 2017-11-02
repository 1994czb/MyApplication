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

    public void register() {
        Observable<ResponseBody> registerObservable = mRegisterModle.register();
        registerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        if (view != null) {
                            view.succee();
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

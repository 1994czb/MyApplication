package com.example.mvpdemo2.presenter;

import com.example.mvpdemo2.modle.RegisterModle;
import com.example.mvpdemo2.view.IRegisterView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/10/9.
 */

public class RegisterPresenter extends IPresenter<IRegisterView> {


    private RegisterModle mRegisterModle;

    public RegisterPresenter(IRegisterView view) {
        super(view);
    }

    @Override
    protected void init() {
        mRegisterModle = new RegisterModle();
    }

    public void register(int id) {
        Observable<ResponseBody> observable = mRegisterModle.register(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        if (view != null) {
                            view.registerSucceed();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.registerFaild();
                        }
                    }
                });
    }
}

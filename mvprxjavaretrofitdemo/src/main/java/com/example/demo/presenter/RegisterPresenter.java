package com.example.demo.presenter;

import android.widget.Toast;

import com.example.demo.modle.RegisterModle;
import com.example.demo.view.IRegisterView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/10/8.
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

    /**
     * presenter利用model返回的被观察者 ，用rxjava的形式处理请求结果
     */
    public void register() {
        //model提供对应的被观察者
        Observable<ResponseBody> registerObservable = mRegisterModle.register();
        //用rxjava的形式处理被观察者
        registerObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        if (view != null) {
//                            view.registerSucceed();
                        Toast.makeText(view.context(),"",Toast.LENGTH_SHORT).show();
                    }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.registerFaid();
                        }
                    }
                });
    }
}

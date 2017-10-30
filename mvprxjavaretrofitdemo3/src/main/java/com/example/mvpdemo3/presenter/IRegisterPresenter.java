package com.example.mvpdemo3.presenter;

import android.content.Context;

import com.example.mvpdemo3.app.App;
import com.example.mvpdemo3.view.Iview;

/**
 * Created by Administrator on 2017/10/9.
 */

public class IRegisterPresenter<T extends Iview> {
    protected T view;

    public IRegisterPresenter(T view) {
        this.view = view;

        init();
    }

    protected void init() {


    }

    public Context context() {
        if (view != null && view.context() != null) {
            return view.context();
        }
        return App.context();
    }
}

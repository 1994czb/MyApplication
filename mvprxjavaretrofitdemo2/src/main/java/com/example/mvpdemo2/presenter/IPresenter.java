package com.example.mvpdemo2.presenter;

import android.content.Context;

import com.example.mvpdemo2.app.App;
import com.example.mvpdemo2.view.IView;

/**
 * Created by Administrator on 2017/10/9.
 */

public class IPresenter<T extends IView> {
    protected T view;

    public IPresenter(T view) {
        this.view = view;
        init();
    }

    protected void init() {

    }

    public Context context(){
        if (view!=null&view.context()!=null){
            return view.context();
        }

        return App.context();
    }
}

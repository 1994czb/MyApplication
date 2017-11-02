package com.example.mvprxjavaretrofitdemo5.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mvprxjavaretrofitdemo5.app.App;
import com.example.mvprxjavaretrofitdemo5.view.IView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/11/2.
 */

public class IRegisterPresenter<T extends IView> {

    protected T view;

    private Reference<T> reference;
    public void attachview(T view){
        //弱引用 可以指定回收
        reference=new WeakReference<>(view);
        //软引用 内存不足时被回收  new SoftReference<>();
        //虚引用 遇到时就有可能会被回收  new PhantomReference<>();
    }
    public void defautview(){
        if (reference!=null){
            reference.clear();
            reference=null;
            Log.e("..................", "解除绑定了");
        }
    }
    public T getview(){
        return reference.get();
    }
    public boolean isconnection(){
        return reference.get()!=null&&reference!=null;
    }

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

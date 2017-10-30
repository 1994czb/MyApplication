package com.example.demo.presenter;

import android.content.Context;

import com.example.demo.app.App;
import com.example.demo.view.IView;

/**
 * Created by Administrator on 2017/10/8.
 */
//// // TODO: 2017/10/9 每一个对应的presenter都有一个对应的接口，而每一个presenter是不确定的，所以对应的接口也不确定，只能填泛型
//// // TODO: 2017/10/9 而每个接口都要继承IView，为其提供一个环境变量
public class IPresenter<T extends IView> {
    protected T view;

    public IPresenter(T view) {
        this.view = view;
        init();
    }

    protected void init() {

    }

    public Context context() {
        //// // TODO: 2017/10/9 判断一下子当前对应的接口类是否为空，
        //// // TODO: 2017/10/9  不为空就返回当前activity的环境变量！
        //// // TODO: 2017/10/9 为空就返回注册的全局环境变量！
        //// // TODO: 2017/10/9 否则会有空指针异常
        if (view != null & view.context() != null) {
            return view.context();
        }
        return App.context();
    }
}

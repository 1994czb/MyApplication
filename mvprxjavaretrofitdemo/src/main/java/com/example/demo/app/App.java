package com.example.demo.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/10/8.
 */
//// // TODO: 2017/10/9 注册了一个全局的环境变量
public class App extends Application {
    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }

    public static Context context() {
        return context;
    }
}

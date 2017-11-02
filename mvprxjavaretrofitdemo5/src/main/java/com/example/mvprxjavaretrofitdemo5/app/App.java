package com.example.mvprxjavaretrofitdemo5.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by hasee on 2017/10/8.
 */

public class App extends Application {
    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }

    public static Context context(){
        return context;
    }
}

package com.example.mvpdemo3.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/10/9.
 */

public class App extends Application {
    private static App context;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context=this;
    }
    public static Context context(){
        return context;
    }
}

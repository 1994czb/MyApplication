package com.zhuanchang.demo;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //分解  转场动画
//        getWindow().setEnterTransition(new Explode().setDuration(2000));
//        getWindow().setExitTransition(new Explode().setDuration(2000));

        //滑动进入
//        getWindow().setEnterTransition(new Slide().setDuration(2000));
//        getWindow().setExitTransition(new Slide().setDuration(2000));

        //淡入淡出
//        getWindow().setEnterTransition(new Fade().setDuration(2000));
//        getWindow().setExitTransition(new Fade().setDuration(2000));



    }
}

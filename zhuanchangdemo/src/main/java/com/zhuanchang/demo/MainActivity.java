package com.zhuanchang.demo;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public void tiaoZhaun(View view) {
//        startActivity(new Intent(this, SecondActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//
//    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void btnzhuangchang(View view) {
        startActivity(new Intent(this,SecondActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(view,"myimg")).toBundle());
    }
}

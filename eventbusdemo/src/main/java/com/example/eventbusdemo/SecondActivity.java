package com.example.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.eventbusdemo.bean.EventBean;
import com.example.eventbusdemo.bean.OtherBean;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {

    private TextView textView01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        textView01 = (TextView) findViewById(R.id.textview01);
        EventBus.getDefault().register(this);
    }

    public void textview_second01(View view) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                EventBus.getDefault().post(new EventBean("曹中宾", 24));
                Logger.e("textview01传递的内容");
                finish();
            }
        }.start();


    }

    public void textview_second02(View view) {
        EventBus.getDefault().post(new OtherBean("我是OtherBean传给MainActivity的内容"));
        finish();
    }


    //接收粘性事件；粘性事件的特点：不止当前注册的eventbus可以收到发过的信息，在该消息之后注册的eventbus依旧可以收到该消息（即时消息则不行）
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(EventBean event) {
        //textview01.setText(event.getName() + "," + event.getAge());
        textView01.setText(event.getName() + "," + event.getAge());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

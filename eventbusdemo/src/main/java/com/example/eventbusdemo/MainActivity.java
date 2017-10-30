package com.example.eventbusdemo;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册EventBus
        EventBus.getDefault().register(this);
        textView = (TextView) findViewById(R.id.textview);

        EventBus.getDefault().postSticky(new EventBean("测试粘性事件", 2017));
    }

    public void textview_main(View view) {
        startActivity(new Intent(this,SecondActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getEventBean(EventBean eventBean){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Logger.e(eventBean.getName() + "," + eventBean.getAge());
        textView.setText(eventBean.getName()+","+eventBean.getAge()); 
    }
    //接收消息时必须写@Subscribe标识
    @Subscribe
    public void getOtherBean(OtherBean otherBean){
        textView.setText(otherBean.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
    }
}

package com.example.mvprxjavaretrofitdemo5.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvprxjavaretrofitdemo5.R;
import com.example.mvprxjavaretrofitdemo5.presenter.RegisterPresenter;
import com.example.mvprxjavaretrofitdemo5.view.IRegisterView;

public class MainActivity extends BaseActivity<IRegisterView,RegisterPresenter> implements IRegisterView {

    private RegisterPresenter mRegisterPresenter;
    String lng="121.538123";
    String lat="31.677132";
    String key="28bd9c1ae0c57280cc432ad77d93c045";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
    }

    @Override
    RegisterPresenter createpresenter() {
        return mRegisterPresenter = new RegisterPresenter(this);
    }

    //点击请求数据
    public void onClick(View view) {

        //mRegisterPresenter.register();
        mRegisterPresenter.register(lng,lat,key);

    }



    @Override
    public void succee(String gson) {
        textView.setText(gson);
    }

    @Override
    public void faild() {
        Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }


}

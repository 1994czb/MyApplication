package com.example.demo.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.presenter.RegisterPresenter;
import com.example.demo.view.IRegisterView;

public class MainActivity extends AppCompatActivity implements IRegisterView {

    private RegisterPresenter mRegisterPresenter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        mRegisterPresenter = new RegisterPresenter(this);
    }

    //点击请求数据
    public void onclick(View view) {
        mRegisterPresenter.register();
    }

    @Override
    public void registerSucceed(String string) {
        Toast.makeText(MainActivity.this, "数据请求成功", Toast.LENGTH_SHORT).show();
        textView.setText(string);

    }

    @Override
    public void registerFaid() {

        Toast.makeText(MainActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }


}

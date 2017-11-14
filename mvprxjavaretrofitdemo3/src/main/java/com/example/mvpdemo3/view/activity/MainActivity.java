package com.example.mvpdemo3.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.mvpdemo3.R;
import com.example.mvpdemo3.presenter.RegisterPresenter;
import com.example.mvpdemo3.view.IRegisterView;

public class MainActivity extends AppCompatActivity implements IRegisterView {

    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegisterPresenter = new RegisterPresenter(this);
    }

    //"李宁","123456","13521546653","女"
    public void onClick(View view) {
        mRegisterPresenter.register("123456", "13521546653");
    }

    @Override
    public void registerSucced() {
        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerFaild() {
        Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }
}

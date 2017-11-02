package com.example.mvprxjavaretrofitdemo5.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvprxjavaretrofitdemo5.R;
import com.example.mvprxjavaretrofitdemo5.presenter.RegisterPresenter;
import com.example.mvprxjavaretrofitdemo5.view.IRegisterView;

public class MainActivity extends BaseActivity<IRegisterView,RegisterPresenter> implements IRegisterView {

    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    RegisterPresenter createpresenter() {
        return mRegisterPresenter = new RegisterPresenter(this);
    }

    public void onClick(View view) {

        mRegisterPresenter.register();

    }

    @Override
    public void succee() {
        Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
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

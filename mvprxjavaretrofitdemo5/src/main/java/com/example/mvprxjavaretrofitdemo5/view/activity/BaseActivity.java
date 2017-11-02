package com.example.mvprxjavaretrofitdemo5.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mvprxjavaretrofitdemo5.presenter.IRegisterPresenter;
import com.example.mvprxjavaretrofitdemo5.view.IView;

/**
 * Created by Administrator on 2017/11/2.
 */
//// // TODO: 2017/11/2 解决MVP内存泄漏的类 ，相关方法在IRegisterPresenter类中！最后在对应的Activity中继承该类就行了
public abstract class BaseActivity<V extends IView, T extends IRegisterPresenter<V>> extends AppCompatActivity {

    private T iPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter = createpresenter();
        iPresenter.attachview((V) this);
    }

    abstract T createpresenter();

    @Override
    protected void onDestroy() {
        iPresenter.defautview();
        super.onDestroy();
    }

}

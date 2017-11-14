package com.example.demo.view;

/**
 * Created by Administrator on 2017/10/8.
 */
//// // TODO: 2017/10/9 继承IView以后，使每一个子类都可以调用到环境变量
public interface IRegisterView extends IView{
    void registerSucceed(String string);
    void registerFaid();
}

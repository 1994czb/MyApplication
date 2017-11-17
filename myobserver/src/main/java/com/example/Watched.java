package com.example;

/**
 * Created by Administrator on 2017/11/17.
 */
//// // TODO: 2017/11/17 定义一个被观察者的接口
public interface Watched {

    public void addWatcher(Watcher watcher);

    public void removeWatcher(Watcher watcher);

    public void notifyWatchers(String str);

}

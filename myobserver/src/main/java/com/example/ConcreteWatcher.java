package com.example;

/**
 * Created by Administrator on 2017/11/17.
 */

public class ConcreteWatcher implements Watcher {
    @Override
    public void update(String str) {
        System.out.print(str);
    }
}

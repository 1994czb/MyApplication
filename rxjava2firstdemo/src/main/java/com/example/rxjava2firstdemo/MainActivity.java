package com.example.rxjava2firstdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

////TODO: 2017/9/27 Rxjava2 整体采用链式调用的结构

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //创建一个上游Observable
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            //emitter 发射器，发射体
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//
//
//                //通过emitter.onNext();发送消息
//                Log.e("TAg","onNext===="+1);
//                emitter.onNext(1);
//                Log.e("TAg","onNext===="+2);
//                emitter.onNext(2);
//
//                //// // TODO: 2017/9/27 onError()方法向下游发送某个异常，下游只会接收异常前发送的内容
//                emitter.onError(new NullPointerException());
//
//                Log.e("TAg","onNext===="+3);
//                emitter.onNext(3);
//               //// // TODO: 2017/9/27   onComplete()方法之后，onNext()可以继续发送消息；
//               /// // TODO: 2017/9/27    但是，下游的onNext()方法不再接收onComplete()以后发送的消息
//                emitter.onComplete();
//                Log.e("TAg","onNext===="+4);
//                emitter.onNext(4);
//                Log.e("TAg","onNext===="+5);
//                emitter.onNext(5);
//
//            }
//        });
//
//
//        //创建一个下游Observer
//        Observer<Integer> observer = new Observer<Integer>() {
//            //// // TODO: 2017/9/27 首先走这个方法，判断一下子上游与下游是否关联
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            //接收上游发过来的消息
//            @Override
//            public void onNext(Integer value) {
//
//                Log.e("TAg",""+value);
//            }
//
//            //上游发过来的一个异常
//            @Override
//            public void onError(Throwable e) {
//                Log.e("TAg",""+e);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        observable.subscribe(observer);


        //// // TODO: 2017/9/27 默认情况下上游河下游都在主线程中 ，由于请求数据等操作在子线程中，请看下文
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                Log.e(TAG, "上游的线程是" + Thread.currentThread().getName());
                Log.e(TAG, "emitter 1");
                emitter.onNext(1);
                Log.e(TAG, "emitter 2");
                emitter.onNext(2);
                Log.e(TAG, "emitter 3");
//                emitter.onError(new NullPointerException());
//                emitter.onNext(3);
//                Log.e(TAG, "emitter onComplete");
//
//                emitter.onComplete();
//                Log.e(TAG, "emitter 4");
//                emitter.onNext(4);
//                Log.e(TAG, "emitter 5");
//                emitter.onNext(5);
            }
            //为上游new 了一个子线程
        }).subscribeOn(Schedulers.newThread())
                //下游在主线程中执行接收到的消息
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "下游的线程是" + Thread.currentThread().getName());
                        Log.e(TAG, integer + "");
                    }
                });
    }
}

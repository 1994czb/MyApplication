package com.example.rxjava2seconddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjava2seconddemo.app.App;
import com.example.rxjava2seconddemo.bean.Bean;
import com.google.gson.Gson;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private CompositeDisposable compositeDisposable;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //相当于一个容器，将上游与下游是否关联的状态放入容器内
        compositeDisposable = new CompositeDisposable();

        //demo1();


        //map操作符：一个简单的字段变换操作符（int String字段等）
        //map();


        // flatMap并不保证事件的顺序(下游接收上游发送消息时，不一定按照上游发送的消息)
        //concatMap的结果是严格按照上游发送的顺序来发送的
        //flatmap_concatmap();


        //两个上游结合到一起，然后发送消息，下游接收消息
        //两个上游发送的消息数量必须两两成对才会发送，如果是单个的就不会发送
        //zip();


        //背压(指上游与下游流速不一致，导致上游积压过多未处理的消息；上游发送消息速度过快，导致下游处理不完)
        //backPressure();

        //背压sample()方法的用法（与线程睡眠效果一致）
        // backPressure2();

        //上游与下游的另一种创建方法
        flowable();

        //interval()方法只能接受long类型的消息，所以不用创建上游，直接由下游接收long类型的消息(可以理解为与计时器类似，用到的比较少)
        //interval();


        //RXjava2与okhttp结合请求数据
        okHttpAndRxJava2();
    }

    private void okHttpAndRxJava2() {
        Observable.create(new ObservableOnSubscribe<Bean>() {
            @Override
            public void subscribe(ObservableEmitter<Bean> emitter) throws Exception {
                Request request = new Request.Builder()
                        .url("http://v.juhe.cn/toutiao/index?type=top&key=5d5c2fb60a87e4800257c4ad598f5232")
                        .get()
                        .build();
                Response response = App.getOkHttpClient().newCall(request).execute();
                String string = response.body().string();
                Bean bean = new Gson().fromJson(string, Bean.class);
                emitter.onNext(bean);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean value) {

                        Log.e("TAG", value.getResult().getData().get(0).getTitle());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void interval() {
        Flowable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

                Log.e("TAG", aLong + "");
            }
        });
    }

    private void flowable() {
        //创建一个上游
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                //当模式是ERROR的时候  flowable 只能缓存128个事件  不然抛出missBackpressureException
                //BUFFER  容器变大  可以存入更多的事件 不会抛出异常
                //drop 降低的意思  下游只接收当前的事件，抛弃之前发的事件
                //latest 每次都会接收最新的一个事件
                for (int i = 0; i < 10000; i++) {
                    Log.e("TAG", "emitter  " + i);
                    e.onNext(i);
                }

            }
        }, BackpressureStrategy.ERROR);
        //创建一个下游
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                mSubscription = s;
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("TAG", integer + "");
            }

            @Override
            public void onError(Throwable t) {

                Log.e("TAG", t + "");
            }

            @Override
            public void onComplete() {

            }
        };
        //关联一下子上游与下游
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    private void backPressure2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    Log.e("TAG", "emitter" + i);
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .sample(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("TAG", value + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("TAG", "onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void backPressure() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

            }
        });
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

            }
        });

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        Log.e("TAG", value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void zip() {
        //创建第一个上游
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                Thread.sleep(1000);
                Log.e(TAG, "emitter 1");
                e.onNext(1);
                Thread.sleep(1000);
                Log.e(TAG, "emitter 2");
                e.onNext(2);
                Thread.sleep(1000);
                Log.e(TAG, "emitter 3");
                e.onNext(3);
                Thread.sleep(1000);
                Log.e(TAG, "emitter 4");
                e.onNext(4);
                Log.e(TAG, "onComplete1");
                e.onComplete();
            }
        }).observeOn(Schedulers.io());


        //创建第二个上游
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                Thread.sleep(1000);
                Log.e(TAG, "emitter A");
                e.onNext("A");
                Thread.sleep(1000);
                Log.e(TAG, "emitter B");
                e.onNext("B");
                Thread.sleep(1000);
                Log.e(TAG, "emitter C");
                e.onNext("C");
                Log.e(TAG, "onComplete2");
                e.onComplete();
            }
        }).observeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        });
    }

    private void flatmap_concatmap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }

                return Observable.fromIterable(list).delay(2, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("TAG", s);
            }
        });
    }

    //简单的字段转换方法
    private void map() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.e("TAG", "onNext" + 1);
                emitter.onNext(1);
                Log.e("TAG", "onNext" + 2);
                emitter.onNext(2);
                Log.e("TAG", "onNext" + 3);
                emitter.onNext(3);

            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer + "转换字段";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("TAG", s);
            }
        });
// .subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String value) {
//                Log.e("TAG",value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

    }

    //建立上游与下游，进行传递信息
    private void demo1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //d的值默认为false，表示上游与下游关联
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("TAG", value + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //下游接收完上游发送的消息以后，必须clear(),销毁掉
        compositeDisposable.clear();
    }
}

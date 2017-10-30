package com.example.retrofit2;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.retrofit2.api.DownFileApi;
import com.example.retrofit2.api.GetBaiduApi;
import com.example.retrofit2.api.RxjavaAndRetrofitApi;
import com.example.retrofit2.api.SimpleGetApi;
import com.example.retrofit2.api.SimpleGetPost2Api;
import com.example.retrofit2.bean.NewsBean;
import com.example.retrofit2.bean.NewsBean2;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getBaidu();
        //SimpleGet();
        //SimpleGet2();
        //downFile();
        rxjavaAndRetrofit();
    }

    private void rxjavaAndRetrofit() {
        //http://api.jisuapi.com/news/get?channel=头条&appkey=cf2efa79d8df0ede
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.jisuapi.com/news/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HashMap<String,String> map=new HashMap<>();
        map.put("channel", "头条");
        map.put("appkey", "cf2efa79d8df0ede");
        Observable<NewsBean2> observable = retrofit.create(RxjavaAndRetrofitApi.class).getData(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean2>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {

                    }

                    @Override
                    public void onNext(@NonNull NewsBean2 newsBean2) {

                        Log.e(TAG, newsBean2.getResult().getList().get(0).getTitle());
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                        Log.e(TAG, "onError: " + throwable);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        Call<NewsBean2> call = retrofit.create(SimpleGetPost2Api.class).getData(map);
//        call.enqueue(new Callback<NewsBean2>() {
//            @Override
//            public void onResponse(Call<NewsBean2> call, Response<NewsBean2> response) {
//                //Log.e(TAG, newsBean2.getResult().getList().get(0).getTitle());
//                Log.e(TAG, response.body().getResult().getList().get(0).getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<NewsBean2> call, Throwable t) {
//
//            }
//        });
    }

    private void downFile() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://surl.qq.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        HashMap<String, String> map = new HashMap<>();
        map.put("qbsrc", "51");
        map.put("asr", "4286");
        Call<ResponseBody> call = retrofit.create(DownFileApi.class).doweFile(map);
        HttpUrl url = call.request().url();
        Log.e(TAG, url + "");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    Log.e(TAG, "isSuccessful: ");

                    try {
                        ResponseBody body = response.body();
                        InputStream is = body.byteStream();
                        FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/腾讯新闻.apk");
                        byte[] bytes = new byte[2048];
                        int len = 0;
                        while ((len = is.read(bytes)) != -1) {
                            fos.write(bytes, 0, len);
                        }
                        is.close();
                        fos.flush();
                        fos.close();
                        Log.e(TAG, "下载完成 ");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void SimpleGet2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.jisuapi.com/news/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HashMap<String, String> map = new HashMap<>();
        map.put("channel", "头条");
        map.put("appkey", "cf2efa79d8df0ede");
        Call<NewsBean2> call = retrofit.create(SimpleGetPost2Api.class).getData(map);
        call.enqueue(new Callback<NewsBean2>() {
            @Override
            public void onResponse(Call<NewsBean2> call, Response<NewsBean2> response) {
                NewsBean2 bean2 = response.body();
                // Log.e(TAG, response.body().getResult().getList().get(0).getTitle());
                Log.e(TAG, bean2.getResult().getList().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<NewsBean2> call, Throwable t) {

            }
        });
    }

    private void SimpleGet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/history/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Call<NewsBean> call = retrofit.create(SimpleGetApi.class).getData(1, 1);
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                NewsBean bean = response.body();
                Log.e(TAG, "onResponse: " + bean.getResults().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {

            }
        });
    }

    private void getBaidu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com")
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Call<String> call = retrofit.create(GetBaiduApi.class).getBaidu();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                Log.e(TAG, "onResponse: =" + body);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });
    }
}

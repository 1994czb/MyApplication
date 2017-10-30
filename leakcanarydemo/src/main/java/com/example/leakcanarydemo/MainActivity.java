package com.example.leakcanarydemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AsyncTask<Void, Void, Integer> asyncTask;
    private Handler mHandler = new Handler();
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);//模拟内存泄露

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mTextView.setText("lcj");
//            }
//        }, 3 * 60 * 1000);
//        finish();


        testAsyncTask();
        finish();
    }

    private void testAsyncTask() {
        asyncTask = new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                int i = 0;
                //模拟耗时操作
                while (!isCancelled()) {
                    i++;
                    if (i > 1000000000) {
                        break;
                    }
                    Log.e("LeakCanary", "asyncTask---->" + i);
                }
                return i;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                mTextView.setText(String.valueOf(integer));
            }
        };
        asyncTask.execute();

    }


    private void destroyAsyncTask() {
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel(true);
        }
        asyncTask = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getRefWatcher(this).watch(this);

    }
}

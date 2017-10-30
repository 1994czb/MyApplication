package com.example.measuredemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        myView = (MyView) findViewById(R.id.myView);
    }

    public void DaDaDa(View view) {
        myView.changeView(10, 10);
    }
}

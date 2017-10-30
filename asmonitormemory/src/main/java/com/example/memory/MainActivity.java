package com.example.memory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String string = new String();

    }

    public void click(View view){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),SecondActivity.class);
        startActivity(intent);
    }
}

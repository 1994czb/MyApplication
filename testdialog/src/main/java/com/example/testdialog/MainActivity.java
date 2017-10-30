package com.example.testdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.mylibrary.DialogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogUtils.showMyDiaLog(this,"提示：","改权限必须开启！");
    }
}

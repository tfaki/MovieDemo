package com.example.talha.moviedemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public static BaseActivity currentActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        currentActivity = this;
    }
    @Override
    protected void onResume(){
        super.onResume();
        currentActivity = this;
    }
}
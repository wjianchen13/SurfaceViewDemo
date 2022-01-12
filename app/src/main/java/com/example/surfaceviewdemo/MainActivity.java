package com.example.surfaceviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.surfaceviewdemo.test1.Test1Activity;
import com.example.surfaceviewdemo.test2.Test2Activity;
import com.example.surfaceviewdemo.test3.Test3Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onTest1(View v) {
        startActivity(new Intent(this, Test1Activity.class));
    }

    public void onTest2(View v) {
        startActivity(new Intent(this, Test2Activity.class));
    }
    
    public void onTest3(View v) {
        startActivity(new Intent(this, Test3Activity.class));
    }

}
package com.example.surfaceviewdemo.test3;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.surfaceviewdemo.R;
import com.example.surfaceviewdemo.test1.Test1Activity;

public class Test3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
    }
    
    public void onToast(View v) {
        Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show();
    }
}
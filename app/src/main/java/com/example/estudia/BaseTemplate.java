package com.example.estudia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BaseTemplate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_template);
    }
}
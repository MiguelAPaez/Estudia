package com.example.estudia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.estudia.R;

public class BaseTemplate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_template);
    }
}
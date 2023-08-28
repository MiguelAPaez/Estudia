package com.example.estudia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.estudia.R;

public class PersonalitySurvey extends AppCompatActivity {

    Button yesButton, noButton;
    ProgressBar progressBar;
    int currentProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_survey);

        yesButton = (Button) findViewById(R.id.yesButton);
        noButton = (Button) findViewById(R.id.noButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSurvey);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!yesButton.isSelected()) {
                    yesButton.setTextColor(getApplicationContext().getResources().getColor(R.color.white_300));
                    noButton.setTextColor(getApplicationContext().getResources().getColor(R.color.grey_100));
                    noButton.setSelected(yesButton.isSelected());
                    yesButton.setSelected(!yesButton.isSelected());
                }
                currentProgress = currentProgress + 10;
                progressBar.setProgress(currentProgress);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!noButton.isSelected()) {
                    noButton.setTextColor(getApplicationContext().getResources().getColor(R.color.white_300));
                    yesButton.setTextColor(getApplicationContext().getResources().getColor(R.color.grey_100));
                    yesButton.setSelected(noButton.isSelected());
                    noButton.setSelected(!noButton.isSelected());
                }
                currentProgress = currentProgress - 10;
                progressBar.setProgress(currentProgress);
            }
        });

    }
}
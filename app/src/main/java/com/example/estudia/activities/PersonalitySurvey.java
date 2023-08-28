package com.example.estudia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.estudia.R;
import com.example.estudia.adapters.PersonalitySurveyViewPagerAdapter;

public class PersonalitySurvey extends AppCompatActivity {

    ProgressBar progressBar;
    ViewPager mSlideViewPager;
    int currentProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_survey);

        progressBar = (ProgressBar) findViewById(R.id.progressBarSurvey);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPagerPersonalitySurvey);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mSlideViewPager.setAdapter(new PersonalitySurveyViewPagerAdapter(this));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
package com.example.estudia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.estudia.R;
import com.example.estudia.adapters.PersonalitySurveyViewPagerAdapter;
import com.example.estudia.enums.SurveyPersonalityQuestionsEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalitySurvey extends AppCompatActivity {

    ProgressBar progressBar;
    ViewPager mSlideViewPager;
    int currentProgress = 0;
    private List<String> personalities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_survey);

        progressBar = (ProgressBar) findViewById(R.id.progressBarSurvey);
        progressBar.setMax(60);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPagerPersonalitySurvey);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        PersonalitySurveyViewPagerAdapter personalitySurvey = new PersonalitySurveyViewPagerAdapter(this);
        mSlideViewPager.setAdapter(personalitySurvey);

        personalitySurvey.setButtonClickListener(new PersonalitySurveyViewPagerAdapter.OnButtonClickListener() {
            @Override
            public void OnButtonClick() {
                if (getItem(0) < 60) {
                    if (getItem(0) == 10) {
                        Intent i = new Intent(getApplicationContext(), Personality.class);
                        startActivity(i);
                        finish();
                    }
                    int newPosition = mSlideViewPager.getCurrentItem() + 1;
                    mSlideViewPager.setCurrentItem(newPosition, false);
                    progressBar.setProgress(newPosition);
                } else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
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

    private int getItem(int i) {
        return mSlideViewPager.getCurrentItem() + i;
    }

}
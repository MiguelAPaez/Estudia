package com.example.estudia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
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

        PersonalitySurveyViewPagerAdapter personalitySurvey = new PersonalitySurveyViewPagerAdapter(this, mSlideViewPager);
        mSlideViewPager.setAdapter(personalitySurvey);

        personalitySurvey.setButtonClickListener(new PersonalitySurveyViewPagerAdapter.OnButtonClickListener() {
            @Override
            public void OnButtonClick() {
                int newPosition = mSlideViewPager.getCurrentItem() + 1;
                mSlideViewPager.setCurrentItem(newPosition, false);
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

}
package com.example.estudia.activities;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_TITLES_PERSONALITY_SURVEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.estudia.R;
import com.example.estudia.adapters.PersonalitySurveyViewPagerAdapter;
import com.example.estudia.enums.SurveyPersonalityQuestionsEnum;
import com.example.estudia.facades.DynamoPersistenceImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalitySurvey extends AppCompatActivity {

    ProgressBar progressBar;
    ViewPager mSlideViewPager;
    TextView titlePageTextView;
    int lengthQuestions = SurveyPersonalityQuestionsEnum.values().length;
    private List<String> personalities = new ArrayList<>();
    DynamoPersistenceImplementation dynamoPersistenceImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_survey);

        dynamoPersistenceImplementation = new DynamoPersistenceImplementation(getApplicationContext());

        progressBar = (ProgressBar) findViewById(R.id.progressBarSurvey);
        progressBar.setMax(60);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPagerPersonalitySurvey);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        titlePageTextView = (TextView) findViewById(R.id.textProgressTitle);

        PersonalitySurveyViewPagerAdapter personalitySurvey = new PersonalitySurveyViewPagerAdapter(this);
        mSlideViewPager.setAdapter(personalitySurvey);

        personalitySurvey.setButtonClickListener(new PersonalitySurveyViewPagerAdapter.OnButtonClickListener() {
            @Override
            public void OnButtonClick() {
                if (getItem(1) < lengthQuestions) {
                    int newPosition = mSlideViewPager.getCurrentItem() + 1;
                    mSlideViewPager.setCurrentItem(newPosition, false);
                    progressBar.setProgress(newPosition);
                    setTitlePage(newPosition);
                } else {
                    dynamoPersistenceImplementation.saveUserInfo();
                    Intent i = new Intent(getApplicationContext(), IntroPersonalities.class);
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

    private void setTitlePage(int pos) {
        if (pos > 0 && pos <= 10) {
            titlePageTextView.setText(ARRAY_TITLES_PERSONALITY_SURVEY[0]);
        } else if (pos > 10 && pos < 20) {
            titlePageTextView.setText(ARRAY_TITLES_PERSONALITY_SURVEY[1]);
        } else if (pos > 20 && pos < 30) {
            titlePageTextView.setText(ARRAY_TITLES_PERSONALITY_SURVEY[2]);
        } else if (pos > 30 && pos < 40) {
            titlePageTextView.setText(ARRAY_TITLES_PERSONALITY_SURVEY[3]);
        } else if (pos > 40 && pos < 50) {
            titlePageTextView.setText(ARRAY_TITLES_PERSONALITY_SURVEY[4]);
        } else if (pos > 50 && pos <= 60) {
            titlePageTextView.setText(ARRAY_TITLES_PERSONALITY_SURVEY[5]);
        }
    }

}
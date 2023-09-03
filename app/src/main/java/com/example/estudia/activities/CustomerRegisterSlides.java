package com.example.estudia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.estudia.R;
import com.example.estudia.adapters.CustomerRegisterSlidesViewPagerAdapter;
import com.example.estudia.adapters.WelcomeIntroSlidesViewPagerAdapter;

public class CustomerRegisterSlides extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    Button backBtn, nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register_slides);

        backBtn = findViewById(R.id.backButtonCustomerRegisterSlide);
        nextBtn = findViewById(R.id.nextButtonCustomerRegisterSlide);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0) > 0) {
                    mSlideViewPager.setCurrentItem(getItem(-1), true);
                }
            }
        });

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPagerCustomRegister);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        mSlideViewPager.setAdapter(new CustomerRegisterSlidesViewPagerAdapter(this));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position > 0) {
                backBtn.setVisibility(View.VISIBLE);
            } else {
                backBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private int getItem(int i) {
        return mSlideViewPager.getCurrentItem() + i;
    }
}
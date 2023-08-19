package com.example.estudia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.estudia.adapters.ViewPagerCustomerRegisterAdapter;

public class CustomRegister extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    Button backBtn, nextBtn;

    TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_register);

        backBtn = findViewById(R.id.backButtonCustomerRegister);
        nextBtn = findViewById(R.id.nextButtonCustomerRegister);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(0) > 0) {
                    mSlideViewPager.setCurrentItem(getItem(-1), true);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(0) < 3) {
                    mSlideViewPager.setCurrentItem(getItem(1), true);
                } else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPagerRegister);
        mDotLayout = (LinearLayout) findViewById(R.id.indicatorLayoutRegister);

        setUpIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mSlideViewPager.setAdapter(new ViewPagerCustomerRegisterAdapter(this));

    }

    public void setUpIndicator(int position) {
        dots = new TextView[4];
        mDotLayout.removeAllViews();

        for(int i = 0; i<dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.grey_400, getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.mustard_500, getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);
            if(position > 0) {
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
        Log.i("Entré al getItem", "Holiii soy yop");
        return mSlideViewPager.getCurrentItem() + i;
    }
}
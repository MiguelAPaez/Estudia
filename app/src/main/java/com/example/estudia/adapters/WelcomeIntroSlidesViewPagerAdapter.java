package com.example.estudia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.estudia.R;
import com.example.estudia.enums.WelcomeSlidesEnum;


public class WelcomeIntroSlidesViewPagerAdapter extends PagerAdapter {
    Context mContext;

    public WelcomeIntroSlidesViewPagerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return WelcomeSlidesEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        WelcomeSlidesEnum customRegisterObj = WelcomeSlidesEnum.values()[position];
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_welcome_intro_layout, container, false);

        ImageView slideTitleImage = (ImageView) view.findViewById(R.id.questionImageTitleCustomerRegister);
        TextView slideHeading = (TextView) view.findViewById(R.id.questionTitleCustomerRegister);
        TextView slideDescription = (TextView) view.findViewById(R.id.questionSubtitleCustomerRegister);

        slideTitleImage.setImageResource(customRegisterObj.getImageResId());
        slideHeading.setText(customRegisterObj.getTitleResId());
        slideDescription.setText(customRegisterObj.getDescriptionResId());
        ;

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

package com.example.estudia.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.estudia.R;


public class ViewPagerCustomerRegisterAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;

    int[] images = {
            R.drawable.logo,
            R.drawable.study1,
            R.drawable.study2,
            R.drawable.cat
    };

    int[] headings = {
            R.string.title_page_1,
            R.string.title_page_2,
            R.string.title_page_3,
            R.string.title_page_4
    };

    int[] descriptions = {
            R.string.subtitle_page_1,
            R.string.subtitle_page_2,
            R.string.subtitle_page_3,
            R.string.subtitle_page_4
    };

    public ViewPagerCustomerRegisterAdapter (Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.i("Entré a", "instantiateItem");
        Log.i("position", Integer.toString(position));
        View view = mLayoutInflater.inflate(R.layout.slider_customer_register_layout, container, false);

        ImageView slideTitleImage = (ImageView) view.findViewById(R.id.questionImageTitleCustomerRegister);
        TextView slideHeading = (TextView) view.findViewById(R.id.questionTitleCustomerRegister);
        TextView slideDescription = (TextView) view.findViewById(R.id.questionSubtitleCustomerRegister);

//        Log.i("headings",Integer.toString(headings[position]));
//        Log.i("descriptions",Integer.toString(descriptions[position]));
//        Log.i("images",Integer.toString(images[position]));

        slideTitleImage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDescription.setText(descriptions[position]);

        Log.i("title", slideHeading.getText().toString());
        Log.i("subtitle", slideDescription.getText().toString());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

package com.example.estudia.enums;

import com.example.estudia.R;

public enum CustomRegisterEnum {
    PAGE1(R.drawable.logo, R.string.title_page_1, R.string.subtitle_page_1),
    PAGE2(R.drawable.study1, R.string.title_page_2, R.string.subtitle_page_2),
    PAGE3(R.drawable.study2, R.string.title_page_3, R.string.subtitle_page_3),
    PAGE4(R.drawable.cat, R.string.title_page_4, R.string.subtitle_page_4);

    private int mImageResId;
    private int mTitleResId;
    private int mDescriptionResId;

    CustomRegisterEnum(int mImageResId, int mTitleResId, int mDescriptionResId) {
        this.mImageResId = mImageResId;
        this.mTitleResId = mTitleResId;
        this.mDescriptionResId = mDescriptionResId;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getDescriptionResId() {
        return mDescriptionResId;
    }
}

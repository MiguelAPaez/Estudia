package com.example.estudia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.estudia.R;
import com.example.estudia.enums.SurveyPersonalityQuestionsEnum;

import java.util.HashMap;
import java.util.Map;

public class PersonalitySurveyViewPagerAdapter extends PagerAdapter {

    Context mContext;
    private Map<Integer, Integer> answers = new HashMap<Integer, Integer>();

    public PersonalitySurveyViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return SurveyPersonalityQuestionsEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SurveyPersonalityQuestionsEnum question = SurveyPersonalityQuestionsEnum.values()[position];
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_personality_survey_layout, container, false);

        TextView titleQuestion = (TextView) view.findViewById(R.id.textQuestionSurvey);
        Button yesButton = (Button) view.findViewById(R.id.yesButton);
        Button noButton = (Button) view.findViewById(R.id.noButton);

        titleQuestion.setText(question.getQuestionTitle());
        yesButton.setSelected(isSelectedButton(position, 1));
        noButton.setSelected(isSelectedButton(position, 0));

        if (yesButton.isSelected()) {
            yesButton.setTextColor(mContext.getApplicationContext().getResources().getColor(R.color.white_300));
        }

        if (noButton.isSelected()) {
            noButton.setTextColor(mContext.getApplicationContext().getResources().getColor(R.color.white_300));
        }

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!yesButton.isSelected()) {
                    yesButton.setTextColor(mContext.getApplicationContext().getResources().getColor(R.color.white_300));
                    noButton.setTextColor(mContext.getApplicationContext().getResources().getColor(R.color.grey_100));
                    noButton.setSelected(yesButton.isSelected());
                    yesButton.setSelected(!yesButton.isSelected());
                }
                answers.put(question.getIdQuestion(), 1);
                System.out.println("ANSWERSSSS!!");
                System.out.println(answers);
                instantiateItem(container, position + 1);
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!noButton.isSelected()) {
                    noButton.setTextColor(mContext.getApplicationContext().getResources().getColor(R.color.white_300));
                    yesButton.setTextColor(mContext.getApplicationContext().getResources().getColor(R.color.grey_100));
                    yesButton.setSelected(noButton.isSelected());
                    noButton.setSelected(!noButton.isSelected());
                }
                answers.put(question.getIdQuestion(), 0);
                System.out.println("ANSWERSSSS!!");
                System.out.println(answers);
                instantiateItem(container, position + 1);
            }
        });
        container.addView(view);
        return view;
    }

    private boolean isSelectedButton(int pos, int button) {
        if (answers.get(pos) != null) {
            if (answers.get(pos) == 0 && button == 0) {
                return true;
            } else {
                return button == 1 & answers.get(pos) == 1;
            }
        } else {
            return false;
        }
    }

    ;

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

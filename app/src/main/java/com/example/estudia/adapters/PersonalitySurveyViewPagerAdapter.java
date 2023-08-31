package com.example.estudia.adapters;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITIES;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.estudia.R;
import com.example.estudia.enums.SurveyPersonalityQuestionsEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import aws.smithy.kotlin.runtime.time.Clock;

public class PersonalitySurveyViewPagerAdapter extends PagerAdapter {

    Context mContext;
    private Map<Integer, Integer> answers = new HashMap<Integer, Integer>();

    public PersonalitySurveyViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public interface OnButtonClickListener {
        void OnButtonClick();
    }

    private OnButtonClickListener mButtonClickListener;

    public void setButtonClickListener(OnButtonClickListener listener) {
        mButtonClickListener = listener;
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

        if (position == 11) {
            calculatePersonality();
        }

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
                if (mButtonClickListener != null) {
                    mButtonClickListener.OnButtonClick();
                }
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
                if (mButtonClickListener != null) {
                    mButtonClickListener.OnButtonClick();
                }
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

    private void calculatePersonality() {
        SurveyPersonalityQuestionsEnum[] questions = SurveyPersonalityQuestionsEnum.values();
        Map<Integer, Integer> idQuestions = new HashMap<Integer, Integer>();
        int maxQ = answers.size();
        ArrayList<Integer> results = new ArrayList<Integer>();
        for (SurveyPersonalityQuestionsEnum quest : questions) {
            idQuestions.put(quest.getIdQuestion(), quest.getQuestionType());
        }
        results.add(0, 0);
        results.add(1, 0);
        results.add(2, 0);
        results.add(3, 0);
        results.add(4, 0);
        for (int i = 0; i < maxQ; i++) {
            int typeQ = idQuestions.get(i);
            switch (typeQ) {
                case 0:
                    int prevValue = results.get(0);
                    results.add(0, prevValue + answers.get(i));
                    break;
                case 1:
                    int prevValue1 = results.get(1);
                    results.add(1, prevValue1 + answers.get(i));
                    break;
                case 2:
                    int prevValue2 = results.get(2);
                    results.add(2, prevValue2 + answers.get(i));
                    break;
                case 3:
                    int prevValue3 = results.get(3);
                    results.add(3, prevValue3 + answers.get(i));
                    break;
                case 4:
                    int prevValue4 = results.get(4);
                    results.add(4, prevValue4 + answers.get(i));
                    break;
            }
        }
        int personalityMaxValue = 0;
        int personality = 0;
        for (int j = 0; j < results.size(); j++) {
            if (personalityMaxValue < results.get(j)) {
                personalityMaxValue = results.get(j);
                personality = j;
            }
        }
        System.out.println("<------------ Tu personalidad es: --------------->");
        System.out.println(PERSONALITIES[personality]);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

package com.example.estudia.adapters;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITIES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_1;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_2;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_3;

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
import com.example.estudia.services.PreferencesEstudiaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import aws.smithy.kotlin.runtime.time.Clock;

public class PersonalitySurveyViewPagerAdapter extends PagerAdapter {

    Context mContext;
    private Map<Integer, Integer> answers = new HashMap<Integer, Integer>();
    PreferencesEstudiaService preferencesEstudiaService;

    public PersonalitySurveyViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
        this.preferencesEstudiaService = new PreferencesEstudiaService(this.mContext);
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

        if (position == SurveyPersonalityQuestionsEnum.values().length - 1) {
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
        final Map<Integer, Integer> results = new HashMap<Integer, Integer>();
        for (SurveyPersonalityQuestionsEnum quest : questions) {
            idQuestions.put(quest.getIdQuestion(), quest.getQuestionType());
        }
        results.put(0, 0);
        results.put(1, 0);
        results.put(2, 0);
        results.put(3, 0);
        results.put(4, 0);
        results.put(5, 0);
        for (int i = 0; i < maxQ; i++) {
            int typeQ = idQuestions.get(i);
            switch (typeQ) {
                case 0:
                    int prevValue = results.get(0);
                    results.put(0, prevValue + answers.get(i));
                    break;
                case 1:
                    int prevValue1 = results.get(1);
                    results.put(1, prevValue1 + answers.get(i));
                    break;
                case 2:
                    int prevValue2 = results.get(2);
                    results.put(2, prevValue2 + answers.get(i));
                    break;
                case 3:
                    int prevValue3 = results.get(3);
                    results.put(3, prevValue3 + answers.get(i));
                    break;
                case 4:
                    int prevValue4 = results.get(4);
                    results.put(4, prevValue4 + answers.get(i));
                    break;
                case 5:
                    int prevValue5 = results.get(5);
                    results.put(5, prevValue5 + answers.get(i));
                    break;
            }
        }
        System.out.println("RESULTSSSS!!");
        System.out.println(results);

        final Map<Integer, Integer> sortedResults = new LinkedHashMap<>();

        results.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sortedResults.put(entry.getKey(), entry.getValue()));

        System.out.println("RESULT ORDERED: ");
        System.out.println(sortedResults);

        Object[] personalities = sortedResults.keySet().toArray();

        System.out.println("MAP KEYS:");
        System.out.println(sortedResults.keySet());

        System.out.println("<------------ Tus personalidades son: --------------->");
        System.out.println("1: " + PERSONALITIES[Integer.parseInt(personalities[0].toString())]);
        System.out.println("2: " + PERSONALITIES[Integer.parseInt(personalities[1].toString())]);
        System.out.println("3: " + PERSONALITIES[Integer.parseInt(personalities[2].toString())]);

        this.preferencesEstudiaService.writeAttribute(PERSONALITY_1, PERSONALITIES[Integer.parseInt(personalities[0].toString())]);
        this.preferencesEstudiaService.writeAttribute(PERSONALITY_2, PERSONALITIES[Integer.parseInt(personalities[1].toString())]);
        this.preferencesEstudiaService.writeAttribute(PERSONALITY_3, PERSONALITIES[Integer.parseInt(personalities[2].toString())]);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

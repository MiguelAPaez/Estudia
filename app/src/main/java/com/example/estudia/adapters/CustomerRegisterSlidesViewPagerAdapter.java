package com.example.estudia.adapters;

import com.example.estudia.R;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_DISABILITIES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_EXPERIENCE;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_RACES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_STUDY;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_WORKS;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.SPINNER;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.SWITCH;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.TEXT_EDIT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.estudia.enums.CustomRegisterEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRegisterSlidesViewPagerAdapter extends PagerAdapter {
    private ArrayList<CustomRegisterEnum> questionsToMake = new ArrayList<>();
    private Map<Integer, String> answers = new HashMap<Integer, String>();
    Context mContext;

    int QUESTION_RACE_1 = R.string.race_question_1;
    int QUESTION_RACE_2 = R.string.race_question_2;
    int QUESTION_DISABILITY_2 = R.string.disability_question_2;
    int QUESTION_WORK_2 = R.string.work_question_2;
    int QUESTION_WORK_3 = R.string.work_question_3;
    int QUESTION_STUDY_1 = R.string.study_question_1;
    /*CustomRegisterEnum lastQuestion;*/
    /*List<CustomRegisterEnum> questionsMade = new ArrayList<>();*/

    public CustomerRegisterSlidesViewPagerAdapter(Context context) {
        this.mContext = context;
//        Hacer Enum para estas preguntas del array y colocar que opciones tendrían habilitadas
        questionsToMake.addAll(Arrays.asList(CustomRegisterEnum.values()));
        /*this.lastQuestion = questionsToMake.get(0);*/
    }

    @Override
    public int getCount() {
        return CustomRegisterEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        CustomRegisterEnum question = whichQuestionShouldMake(position);
        int questionPosition = question.getQuestionId();
        /*if(!questionsMade.contains(question)) {
            questionsMade.add(question);
        }*/

        System.out.println("QUESTION TO MAKE!!");
        System.out.println(question);
        System.out.println("POSITION");
        System.out.println(questionPosition);

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_customer_register_layout, container, false);

        TextView titleQuestionText = (TextView) view.findViewById(R.id.questionTitleCustomerRegister);
        titleQuestionText.setText(question.getTitleStringId());

        switch (question.getElement()) {
            case SWITCH:
                Switch switchQuestion = (Switch) view.findViewById(R.id.switchCustomerRegister);
                switchQuestion.setVisibility(elementVisibility(1));
                switchQuestion.setChecked(switchIsChecked(questionPosition));
                answers.put(questionPosition, String.valueOf(switchQuestion.isChecked()));
                switchQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        answers.put(questionPosition, String.valueOf(b));
                        System.out.println("Soy El arregloooo");
                        System.out.println(answers);
                    }
                });
                break;
            case SPINNER:
                Spinner spinnerQuestion = (Spinner) view.findViewById(R.id.spinnerCustomerRegister);
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, fillSpinner(position));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerQuestion.setAdapter(adapter);
                spinnerQuestion.setVisibility(elementVisibility(1));
                break;
            case TEXT_EDIT:
                LinearLayout linearLayoutQuestion = (LinearLayout) view.findViewById(R.id.linearLayoutCustomerRegister);
                EditText editTextQuestion = (EditText) view.findViewById(R.id.motivationRegister);
                linearLayoutQuestion.setVisibility(elementVisibility(1));
                editTextQuestion.setVisibility(elementVisibility(1));
                break;
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private CustomRegisterEnum whichQuestionShouldMake(int pos) {
        int question = pos;
        boolean exists = false;
        /*if(questionsMade.contains(CustomRegisterEnum.values()[question])) {
            question = CustomRegisterEnum.values()[question].getQuestionId();
        }*/
        for (Map.Entry<Integer, String> me :
                answers.entrySet()) {
            if (me.getKey() == pos) {
                exists = true;
                question = me.getKey();
            }
        }

        switch (CustomRegisterEnum.values()[question]) {
            case RACE2:
                if (answers.get(0).equals("true")) {
                    return getQuestion(question);
                }
                return getQuestion(question + 1);
            case DISABILITY2:
                if (answers.get(2).equals("true")) {
                    return getQuestion(question);
                }
                return getQuestion(question + 1);
            case WORK2:
                if (answers.get(4).equals("true")) {
                    return getQuestion(question);
                }
                return getQuestion(question + 2);
            default:
                return getQuestion(question);
        }
    }

    private CustomRegisterEnum getQuestion(int pos) {
        System.out.println(pos);
        System.out.println(CustomRegisterEnum.values()[pos]);
        return CustomRegisterEnum.values()[pos];
    }

    public String[] fillSpinner(int pos) {
        if (this.questionsToMake.get(pos).getTitleStringId() == QUESTION_RACE_2) {
            return ARRAY_RACES;
        }
        if (this.questionsToMake.get(pos).getTitleStringId() == QUESTION_DISABILITY_2) {
            return ARRAY_DISABILITIES;
        }
        if (this.questionsToMake.get(pos).getTitleStringId() == QUESTION_WORK_2) {
            return ARRAY_WORKS;
        }
        if (this.questionsToMake.get(pos).getTitleStringId() == QUESTION_WORK_3) {
            return ARRAY_EXPERIENCE;
        }
        if (this.questionsToMake.get(pos).getTitleStringId() == QUESTION_STUDY_1) {
            return ARRAY_STUDY;
        }
        return ARRAY_WORKS;
    }

    public int elementVisibility(int option) {
        if (option == 1) {
            return View.VISIBLE;
        } else {
            return View.INVISIBLE;
        }
    }

    public boolean switchIsChecked(int position) {
        return answers.get(position) != null && !answers.get(position).equals("false");
    }
}

package com.example.estudia.enums;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.SPINNER;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.SWITCH;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.TEXT_EDIT;

import com.example.estudia.R;

public enum CustomRegisterEnum {

    RACE1(0, 0, R.string.race_question_1, SWITCH),
    RACE2(1, 0, R.string.race_question_2, SPINNER),
    DISABILITY1(2, 0, R.string.disability_question_1, SWITCH),
    DISABILITY2(3, 0, R.string.disability_question_2, SPINNER),
    WORK1(4, 0, R.string.work_question_1, SWITCH),
    WORK2(5, 0, R.string.work_question_2, SPINNER),
    WORK3(6, 0, R.string.work_question_3, SPINNER),
    STUDY1(7, 0, R.string.study_question_1, SPINNER),
    MOTIVATION1(8, 0, R.string.motivation_question_1, TEXT_EDIT);

    private int questionId;
    private int imageId;
    private int titleStringId;
    private String element;

    CustomRegisterEnum(int questionId, int imageId, int titleStringId, String element) {
        this.questionId = questionId;
        this.imageId = imageId;
        this.titleStringId = titleStringId;
        this.element = element;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getImageId() {
        return imageId;
    }

    public int getTitleStringId() {
        return titleStringId;
    }

    public String getElement() {
        return element;
    }
}

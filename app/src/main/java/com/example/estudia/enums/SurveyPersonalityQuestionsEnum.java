package com.example.estudia.enums;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARTISTA;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.CONVENCIONAL;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.EMPRENDEDOR;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.INVESTIGADOR;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.REALISTA;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.SOCIAL;

import com.example.estudia.R;

public enum SurveyPersonalityQuestionsEnum {
    QUESTION1(0, R.string.personality_question_1, REALISTA),
    QUESTION2(1, R.string.personality_question_2, REALISTA),
    QUESTION3(2, R.string.personality_question_3, REALISTA),
    QUESTION4(3, R.string.personality_question_4, REALISTA),
    QUESTION5(4, R.string.personality_question_5, REALISTA),
    QUESTION6(5, R.string.personality_question_6, INVESTIGADOR),
    QUESTION7(6, R.string.personality_question_7, INVESTIGADOR),
    QUESTION8(7, R.string.personality_question_8, INVESTIGADOR),
    QUESTION9(8, R.string.personality_question_9, INVESTIGADOR),
    QUESTION10(9, R.string.personality_question_10, INVESTIGADOR),
    QUESTION11(10, R.string.personality_question_11, ARTISTA),
    QUESTION12(11, R.string.personality_question_12, ARTISTA),
    QUESTION13(12, R.string.personality_question_13, ARTISTA),
    QUESTION14(13, R.string.personality_question_14, ARTISTA),
    QUESTION15(14, R.string.personality_question_15, ARTISTA),
    QUESTION16(15, R.string.personality_question_16, SOCIAL),
    QUESTION17(16, R.string.personality_question_17, SOCIAL),
    QUESTION18(17, R.string.personality_question_18, SOCIAL),
    QUESTION19(18, R.string.personality_question_19, SOCIAL),
    QUESTION20(19, R.string.personality_question_20, SOCIAL),
    QUESTION21(20, R.string.personality_question_21, EMPRENDEDOR),
    QUESTION22(21, R.string.personality_question_22, EMPRENDEDOR),
    QUESTION23(22, R.string.personality_question_23, EMPRENDEDOR),
    QUESTION24(23, R.string.personality_question_24, EMPRENDEDOR),
    QUESTION25(24, R.string.personality_question_25, EMPRENDEDOR),
    QUESTION26(25, R.string.personality_question_26, CONVENCIONAL),
    QUESTION27(26, R.string.personality_question_27, CONVENCIONAL),
    QUESTION28(27, R.string.personality_question_28, CONVENCIONAL),
    QUESTION29(28, R.string.personality_question_29, CONVENCIONAL),
    QUESTION30(29, R.string.personality_question_30, CONVENCIONAL),
    QUESTION31(30, R.string.personality_question_31, REALISTA),
    QUESTION32(31, R.string.personality_question_32, REALISTA),
    QUESTION33(32, R.string.personality_question_33, REALISTA),
    QUESTION34(33, R.string.personality_question_34, REALISTA),
    QUESTION35(34, R.string.personality_question_35, REALISTA),
    QUESTION36(35, R.string.personality_question_36, INVESTIGADOR),
    QUESTION37(36, R.string.personality_question_37, INVESTIGADOR),
    QUESTION38(37, R.string.personality_question_38, INVESTIGADOR),
    QUESTION39(38, R.string.personality_question_39, INVESTIGADOR),
    QUESTION40(39, R.string.personality_question_40, INVESTIGADOR),
    QUESTION41(40, R.string.personality_question_41, ARTISTA),
    QUESTION42(41, R.string.personality_question_42, ARTISTA),
    QUESTION43(42, R.string.personality_question_43, ARTISTA),
    QUESTION44(43, R.string.personality_question_44, ARTISTA),
    QUESTION45(44, R.string.personality_question_45, ARTISTA),
    QUESTION46(45, R.string.personality_question_46, SOCIAL),
    QUESTION47(46, R.string.personality_question_47, SOCIAL),
    QUESTION48(47, R.string.personality_question_48, SOCIAL),
    QUESTION49(48, R.string.personality_question_49, SOCIAL),
    QUESTION50(49, R.string.personality_question_50, SOCIAL),
    QUESTION51(50, R.string.personality_question_51, EMPRENDEDOR),
    QUESTION52(51, R.string.personality_question_52, EMPRENDEDOR),
    QUESTION53(52, R.string.personality_question_53, EMPRENDEDOR),
    QUESTION54(53, R.string.personality_question_54, EMPRENDEDOR),
    QUESTION55(54, R.string.personality_question_55, EMPRENDEDOR),
    QUESTION56(55, R.string.personality_question_56, CONVENCIONAL),
    QUESTION57(56, R.string.personality_question_57, CONVENCIONAL),
    QUESTION58(57, R.string.personality_question_58, CONVENCIONAL),
    QUESTION59(58, R.string.personality_question_59, CONVENCIONAL),
    QUESTION60(59, R.string.personality_question_60, CONVENCIONAL);

    private int idQuestion;
    private int questionTitle;
    private int questionType;

    SurveyPersonalityQuestionsEnum(int idQuestion, int questionTitle, int questionType) {
        this.idQuestion = idQuestion;
        this.questionTitle = questionTitle;
        this.questionType = questionType;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public int getQuestionTitle() {
        return questionTitle;
    }

    public int getQuestionType() {
        return questionType;
    }
}

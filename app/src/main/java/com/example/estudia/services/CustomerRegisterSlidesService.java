package com.example.estudia.services;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_CITIES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_DISABILITIES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_EXPERIENCE;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_PROGRAM_MODALITY;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_PROGRAM_TYPE;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_RACES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_STUDY;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_STUDY_AREAS;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_TIME_AVAILABILITY;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_WORKS;

import com.example.estudia.R;
import com.example.estudia.enums.CustomRegisterEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerRegisterSlidesService {

    private ArrayList<CustomRegisterEnum> questionsToMake = new ArrayList<>();
    private Map<Integer, String> answers = new HashMap<Integer, String>();

    public String[] fillSpinner(int pos) {
        switch (pos) {
            case 0:
                return ARRAY_RACES;
            case 1:
                return ARRAY_DISABILITIES;
            case 2:
                return ARRAY_WORKS;
            case 3:
                return ARRAY_EXPERIENCE;
            case 4:
                return ARRAY_STUDY;
            case 5:
                return ARRAY_PROGRAM_TYPE;
            case 6:
                return ARRAY_PROGRAM_MODALITY;
            case 7:
                return ARRAY_STUDY_AREAS;
            case 8:
                return ARRAY_CITIES;
            case 9:
                return ARRAY_TIME_AVAILABILITY;
            default:
                return ARRAY_WORKS;
        }
    }

    private CustomRegisterEnum getQuestion(int pos) {
        System.out.println(pos);
        System.out.println(CustomRegisterEnum.values()[pos]);
        return CustomRegisterEnum.values()[pos];
    }

    private CustomRegisterEnum whichQuestionShouldMake(int pos) {
        int question = pos;
        boolean exists = false;
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

    public String timeAvailabilityMapper(String entry) {
        String response = "";
        switch (entry) {
            case "Jornada Diurna (6:00 a.m., a 6:00 p.m.)":
                response = "Diurna";
            case "Jornada Nocturna (6:00 p.m., a 10:00 p.m.)":
                response = "Nocturna";
            case "Jornada Madrugada (10:00 p.m., a 6:00 a.m.)":
                response = "Madrugada";
            case "Jornada Mixta (6:00 a.m., a 10:00 p.m.)":
                response = "Mixta";
            default:
                response = "Sin definir";
        }
        return response;
    }

}

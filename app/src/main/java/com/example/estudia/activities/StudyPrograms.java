package com.example.estudia.activities;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITIES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_1;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_2;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_3;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PROGRAMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.estudia.R;
import com.example.estudia.adapters.ProgramsHorizontalAdapter;
import com.example.estudia.entities.StudyProgram;
import com.example.estudia.interfaces.RecyclerViewEstudiaInterface;
import com.example.estudia.services.PreferencesEstudiaService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class StudyPrograms extends AppCompatActivity implements RecyclerViewEstudiaInterface {

    ArrayList<StudyProgram> programsListPersonality1;
    ArrayList<StudyProgram> programsListPersonality2;
    ArrayList<StudyProgram> programsListPersonality3;
    RecyclerView recyclerHorizontalProgramsPersonality1;
    RecyclerView recyclerHorizontalProgramsPersonality2;
    RecyclerView recyclerHorizontalProgramsPersonality3;
    PreferencesEstudiaService preferencesEstudiaService;
    TextView personalityTV1, personalityTV2, personalityTV3;
    String personality1;
    String personality2;
    String personality3;
    JSONArray arrayPrograms;
    private static final String REALISTA = "Realista";
    private static final String INVESTIGADOR = "Investigador";
    private static final String ARTISTA = "Artista";
    private static final String SOCIAL = "Social";
    private static final String EMPRENDEDOR = "Emprendedor";
    private static final String CONVENCIONAL = "Convencional";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_programs);

        preferencesEstudiaService = new PreferencesEstudiaService(getApplicationContext());

        personalityTV1 = (TextView) findViewById(R.id.studyProgramsPersonality1);
        personalityTV2 = (TextView) findViewById(R.id.studyProgramsPersonality2);
        personalityTV3 = (TextView) findViewById(R.id.studyProgramsPersonality3);

        String stringPrograms = preferencesEstudiaService.getAttribute(PROGRAMS);
        personality1 = preferencesEstudiaService.getAttribute(PERSONALITY_1);
        personality2 = preferencesEstudiaService.getAttribute(PERSONALITY_2);
        personality3 = preferencesEstudiaService.getAttribute(PERSONALITY_3);

        personalityTV1.setText(personality1);
        personalityTV2.setText(personality2);
        personalityTV3.setText(personality3);

        System.out.println("PROGRAMAS STUDY!!");
        System.out.println(stringPrograms);

        programsListPersonality1 = new ArrayList<>();
        programsListPersonality2 = new ArrayList<>();
        programsListPersonality3 = new ArrayList<>();

        try {
            getPrograms(stringPrograms);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        recyclerHorizontalProgramsPersonality1 = (RecyclerView) findViewById(R.id.recyclerHorizontalSuggestedProgramsPersonality1);
        recyclerHorizontalProgramsPersonality1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerHorizontalProgramsPersonality2 = (RecyclerView) findViewById(R.id.recyclerHorizontalSuggestedProgramsPersonality2);
        recyclerHorizontalProgramsPersonality2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerHorizontalProgramsPersonality3 = (RecyclerView) findViewById(R.id.recyclerHorizontalSuggestedProgramsPersonality3);
        recyclerHorizontalProgramsPersonality3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // fillPrograms();

        ProgramsHorizontalAdapter horizontalAdapter = new ProgramsHorizontalAdapter(programsListPersonality1, this, this, 1);
        recyclerHorizontalProgramsPersonality1.setAdapter(horizontalAdapter);
        ProgramsHorizontalAdapter horizontalAdapter2 = new ProgramsHorizontalAdapter(programsListPersonality2, this, this, 2);
        recyclerHorizontalProgramsPersonality2.setAdapter(horizontalAdapter2);
        ProgramsHorizontalAdapter horizontalAdapter3 = new ProgramsHorizontalAdapter(programsListPersonality3, this, this, 3);
        recyclerHorizontalProgramsPersonality3.setAdapter(horizontalAdapter3);
    }

    private void getPrograms(String programs) throws JSONException {
        JSONObject json = new JSONObject(programs);
        JSONObject body = json.getJSONObject("body");
        this.arrayPrograms = body.getJSONArray("programs");

        for (int i = 0; i < this.arrayPrograms.length(); i++) {
            JSONObject aux = (JSONObject) this.arrayPrograms.get(i);
            if (aux.get("personality").equals(personality1)) {
                programsListPersonality1.add(createProgram(aux));
            }
            if (aux.get("personality").equals(personality2)) {
                programsListPersonality2.add(createProgram(aux));
            }
            if (aux.get("personality").equals(personality3)) {
                programsListPersonality3.add(createProgram(aux));
            }
        }

    }

    private StudyProgram createProgram(JSONObject program) throws JSONException {
        return new StudyProgram(
                (String) program.getString("name"),
                (String) program.getString("id"),
                (String) program.getString("description"),
                jsonArrayToString(program.getJSONArray("topics")),
                (String) program.getString("requirements"),
                (String) program.getString("schedule"),
                (String) program.getString("study_center"),
                (String) program.getString("personality"),
                (String) program.getString("program_type"),
                (String) program.getString("city"),
                getImage((String) program.getString("personality"))
        );
    }

    private String[] jsonArrayToString(JSONArray array) throws JSONException {
        String[] result = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            result[i] = (String) array.get(i);
        }
        return result;
    }

    private int getImage(String personality) {
        switch (personality) {
            case REALISTA:
                return R.drawable.realista_icon;
            case INVESTIGADOR:
                return R.drawable.investigador_icon;
            case ARTISTA:
                return R.drawable.artista_icon;
            case SOCIAL:
                return R.drawable.social_icon;
            case EMPRENDEDOR:
                return R.drawable.emprendedor_icon;
            case CONVENCIONAL:
                return R.drawable.convencional_icon;
            default:
                return R.drawable.student;
        }
    }

    @Override
    public void onItemClick(int position, int array) {
        if (array == 1) {
            createIntent(programsListPersonality1.get(position));
        }
        if (array == 2) {
            createIntent(programsListPersonality2.get(position));
        }
        if (array == 3) {
            createIntent(programsListPersonality3.get(position));
        }
    }

    private void createIntent(StudyProgram program) {
        Intent intent = new Intent(StudyPrograms.this, StudyProgramActivity.class);
        intent.putExtra("name", program.getName());
        intent.putExtra("image", program.getImage());
        intent.putExtra("code", program.getId());
        intent.putExtra("schedule", program.getSchedule());
        intent.putExtra("city", program.getCity());
        intent.putExtra("study_center", program.getStudy_center());
        intent.putExtra("program_type", program.getProgramType());
        intent.putExtra("description", program.getDescription());
        startActivity(intent);
    }

    /*private void fillPrograms() {
        programsListPersonality1.add(new StudyProgram("Programa 1 SIII ESE SOY YO Y ESTOY PROBANDO QUE TAN GRANDE ES ESTO!!!", "Soy el Programa Número 1", "Virtual", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city",  R.drawable.student));
        programsListPersonality1.add(new StudyProgram("Programa 2", "Holii", "Presencial", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city", R.drawable.student));
        programsListPersonality1.add(new StudyProgram("Programa 3", "Cómo estás?", "Combinada", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city", R.drawable.student));
        programsListPersonality1.add(new StudyProgram("Programa 4", "Pasaba a decirte", "Virtual", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city", R.drawable.student));
        programsListPersonality1.add(new StudyProgram("Programa 5", "Que vas bien", "Presencial", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city", R.drawable.student));
        programsListPersonality1.add(new StudyProgram("Programa 6", "Y que...", "Combinada", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city", R.drawable.student));
        programsListPersonality1.add(new StudyProgram("Programa 7", "Vas a terminar", "Virtual", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city", R.drawable.student));
        programsListPersonality1.add(new StudyProgram("Programa 8", "Tú tesis", "Presencial", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city", R.drawable.student));
        programsListPersonality1.add(new StudyProgram("Programa 9", "Att:", "Combinada", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city", R.drawable.student));
        programsListPersonality1.add(new StudyProgram("Programa 10", "Estudía!!!", "Virtual", new String[]{"335435"}, "requirements", "schedule", "study_center", "personality", "programType", "city", R.drawable.student));
    }*/
}
package com.example.estudia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.estudia.R;
import com.example.estudia.adapters.ProgramsHorizontalAdapter;
import com.example.estudia.adapters.ProgramsVerticalAdapter;
import com.example.estudia.entities.StudyProgram;

import java.util.ArrayList;

public class StudyPrograms extends AppCompatActivity {

    ArrayList<StudyProgram> programsList;
    RecyclerView recyclerHorizontalPrograms;
    RecyclerView recyclerViewVerticalPrograms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_programs);

        programsList = new ArrayList<>();

        recyclerHorizontalPrograms = (RecyclerView) findViewById(R.id.recyclerHorizontalSuggestedPrograms);
        recyclerHorizontalPrograms.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewVerticalPrograms = (RecyclerView) findViewById(R.id.recyclerVerticalSuggestedPrograms);
        recyclerViewVerticalPrograms.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fillPrograms();

        ProgramsHorizontalAdapter horizontalAdapter = new ProgramsHorizontalAdapter(programsList, this);
        recyclerHorizontalPrograms.setAdapter(horizontalAdapter);

        ProgramsVerticalAdapter verticalAdapter = new ProgramsVerticalAdapter(this, programsList);
        recyclerViewVerticalPrograms.setAdapter(verticalAdapter);
    }

    private void fillPrograms() {
        programsList.add(new StudyProgram("Programa 1 SIII ESE SOY YO Y ESTOY PROBANDO QUE TAN GRANDE ES ESTO!!!", "Soy el Programa Número 1", "Virtual", "335435", R.drawable.student));
        programsList.add(new StudyProgram("Programa 2", "Holii", "Presencial", "665466", R.drawable.student));
        programsList.add(new StudyProgram("Programa 3", "Cómo estás?", "Combinada", "343545", R.drawable.student));
        programsList.add(new StudyProgram("Programa 4", "Pasaba a decirte", "Virtual", "335435", R.drawable.student));
        programsList.add(new StudyProgram("Programa 5", "Que vas bien", "Presencial", "665466", R.drawable.student));
        programsList.add(new StudyProgram("Programa 6", "Y que...", "Combinada", "343545", R.drawable.student));
        programsList.add(new StudyProgram("Programa 7", "Vas a terminar", "Virtual", "335435", R.drawable.student));
        programsList.add(new StudyProgram("Programa 8", "Tú tesis", "Presencial", "665466", R.drawable.student));
        programsList.add(new StudyProgram("Programa 9", "Att:", "Combinada", "343545", R.drawable.student));
        programsList.add(new StudyProgram("Programa 10", "Estudía!!!", "Virtual", "335435", R.drawable.student));
    }
}
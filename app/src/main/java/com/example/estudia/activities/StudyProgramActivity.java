package com.example.estudia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.estudia.R;

import org.w3c.dom.Text;

import java.util.Objects;

public class StudyProgramActivity extends AppCompatActivity {

    TextView name, code, schedule, city, studyCenter, programType, description;
    ImageView image, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_program);

        String nameString = getIntent().getStringExtra("name");
        int imageInt = getIntent().getIntExtra("image", 0);
        String codeString = getIntent().getStringExtra("code");
        String scheduleString = getIntent().getStringExtra("schedule");
        String cityString = getIntent().getStringExtra("city");
        String studyCenterString = getIntent().getStringExtra("study_center");
        String programTypeString = getIntent().getStringExtra("program_type");
        String descriptionString = getIntent().getStringExtra("description");

        name = (TextView) findViewById(R.id.nameProgram);
        code = (TextView) findViewById(R.id.tableCodeValue);
        schedule = (TextView) findViewById(R.id.tableScheduleValue);
        city = (TextView) findViewById(R.id.tableCityValue);
        studyCenter = (TextView) findViewById(R.id.tableStudyCenterValue);
        programType = (TextView) findViewById(R.id.tableProgramTypeValue);
        description = (TextView) findViewById(R.id.studyProgramDescription);

        image = (ImageView) findViewById(R.id.imageProgram);
        backButton = (ImageView) findViewById(R.id.backButtonStudyProgram);

        name.setText(nameString);
        code.setText(codeString);
        schedule.setText(scheduleString);
        city.setText(cityString);
        studyCenter.setText(studyCenterString);
        programType.setText(programTypeString);
        description.setText(descriptionString);
        image.setImageResource(imageInt);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
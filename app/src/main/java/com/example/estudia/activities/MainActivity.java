package com.example.estudia.activities;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.NAME_USER;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_1;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_3;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.REGISTER_FILLED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.estudia.R;
import com.example.estudia.facades.CognitoImplementation;
import com.example.estudia.facades.DynamoPersistenceImplementation;
import com.example.estudia.facades.StudyProgramsImplementation;
import com.example.estudia.services.PreferencesEstudiaService;

public class MainActivity extends AppCompatActivity {

    CardView btnLogout, btnRegister, btnSurvey, btnPersonalities, btnPrograms;
    TextView eWelcomeMessage;
    CognitoImplementation cognitoImplementation;
    PreferencesEstudiaService preferencesEstudiaService;
    DynamoPersistenceImplementation dynamoPersistenceImplementation;
    StudyProgramsImplementation studyProgramsImplementation;
    String name;
    boolean dataCharged = false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = (CardView) findViewById(R.id.buttonLogOut);
        btnRegister = (CardView) findViewById(R.id.nextRegister);
        btnSurvey = (CardView) findViewById(R.id.personalitySurveyButton);
        btnPersonalities = (CardView) findViewById(R.id.personalitiesButton);
        btnPrograms = (CardView) findViewById(R.id.programsButton);
        eWelcomeMessage = (TextView) findViewById(R.id.welcomeMessageMain);

        btnSurvey.setEnabled(false);
        btnSurvey.setCardBackgroundColor(getResources().getColor(R.color.grey_200));
        btnPersonalities.setEnabled(false);
        btnPersonalities.setCardBackgroundColor(getResources().getColor(R.color.grey_200));
        btnPrograms.setEnabled(false);
        btnPrograms.setCardBackgroundColor(getResources().getColor(R.color.grey_200));

        //Cognito Service
        cognitoImplementation = new CognitoImplementation(getApplicationContext());

        preferencesEstudiaService = new PreferencesEstudiaService(getApplicationContext());

        dynamoPersistenceImplementation = new DynamoPersistenceImplementation(getApplicationContext());

        studyProgramsImplementation = new StudyProgramsImplementation(getApplicationContext());

        MainActivity.ThreadC c = new ThreadC();
        c.start();

        synchronized (c) {

            try {
                System.out.println("Waiting for c to complete...");
                c.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (dataCharged) {
                eWelcomeMessage.setText("Hello " + name + "!");
            }
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cognitoImplementation.signOut();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerRegisterContainer.class);
                startActivity(intent);
            }
        });

        btnSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), IntroSurvey.class);
                startActivity(intent);
            }
        });

        btnPersonalities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dynamoPersistenceImplementation.saveUserInfo();
                Intent intent = new Intent(getApplicationContext(), IntroPersonalities.class);
                startActivity(intent);
            }
        });

        btnPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudyPrograms.class);
                startActivity(intent);
            }
        });

    }

    class ThreadC extends Thread {
        @Override
        public void run() {
            synchronized (this) {
                getUserData();
                dataCharged = fillData();
                notify();
            }
        }
    }

    public boolean fillData() {
        name = preferencesEstudiaService.getAttribute(NAME_USER);
        return true;
    }

    public void getUserData() {
        this.dynamoPersistenceImplementation.getUser();
        this.studyProgramsImplementation.getPrograms();
        this.enableOptions();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.enableOptions();
    }

    public void enableOptions() {
        if (this.preferencesEstudiaService.getAttribute(PERSONALITY_1) != null) {
            btnPersonalities.setEnabled(true);
            btnPersonalities.setCardBackgroundColor(getResources().getColor(R.color.white_500));
            btnPrograms.setEnabled(true);
            btnPrograms.setCardBackgroundColor(getResources().getColor(R.color.white_500));
        }
        if (this.preferencesEstudiaService.getAttribute(REGISTER_FILLED) != null && this.preferencesEstudiaService.getAttribute(REGISTER_FILLED).equals("true")) {
            btnSurvey.setEnabled(true);
            btnSurvey.setCardBackgroundColor(getResources().getColor(R.color.white_500));
            System.out.println(this.preferencesEstudiaService.getAttribute(PERSONALITY_1));
        }
    }
}
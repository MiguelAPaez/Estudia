package com.example.estudia.activities;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.NAME_USER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.estudia.R;
import com.example.estudia.facades.CognitoImplementation;
import com.example.estudia.facades.DynamoPersistenceImplementation;
import com.example.estudia.services.PreferencesEstudiaService;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    CardView btnLogout, btnRegister, btnSurvey, btnProfile;
    TextView eWelcomeMessage;
    CognitoImplementation cognitoImplementation;
    PreferencesEstudiaService preferencesEstudiaService;
    DynamoPersistenceImplementation dynamoPersistenceImplementation;
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
        btnProfile = (CardView) findViewById(R.id.profileButton);
        eWelcomeMessage = (TextView) findViewById(R.id.welcomeMessageMain);

        //Cognito Service
        cognitoImplementation = new CognitoImplementation(getApplicationContext());

        preferencesEstudiaService = new PreferencesEstudiaService(getApplicationContext());

        dynamoPersistenceImplementation = new DynamoPersistenceImplementation(getApplicationContext());

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
                dynamoPersistenceImplementation.getUsers();
                Intent intent = new Intent(getApplicationContext(), IntroSurvey.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dynamoPersistenceImplementation.saveUserInfo();
                Intent intent = new Intent(getApplicationContext(), SelectPreferencesActivity.class);
                startActivity(intent);
            }
        });

    }

    class ThreadC extends Thread {
        @Override
        public void run() {
            synchronized (this) {
                dataCharged = fillData();
                notify();
            }
        }
    }

    public boolean fillData() {
        name = preferencesEstudiaService.getAttribute(NAME_USER);
        return true;
    }
}
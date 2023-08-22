package com.example.estudia;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.NAME_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.estudia.facades.CognitoImplementation;
import com.example.estudia.services.PreferencesEstudiaService;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    TextView eWelcomeMessage;
    CognitoImplementation cognitoImplementation;
    PreferencesEstudiaService preferencesEstudiaService;
    String name;
    boolean dataCharged = false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = (Button) findViewById(R.id.buttonLogOut);
        eWelcomeMessage = (TextView) findViewById(R.id.welcomeMessageMain);

        //Cognito Service
        cognitoImplementation = new CognitoImplementation(getApplicationContext());

        preferencesEstudiaService = new PreferencesEstudiaService(getApplicationContext());

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
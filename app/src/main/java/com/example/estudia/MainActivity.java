package com.example.estudia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.estudia.facades.CognitoImplementation;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    CognitoImplementation cognitoImplementation;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = (Button) findViewById(R.id.buttonLogOut);

        //Cognito Service
        cognitoImplementation = new CognitoImplementation(getApplicationContext());

        cognitoImplementation.userAttributes();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cognitoImplementation.signOut();
            }
        });

    }
}
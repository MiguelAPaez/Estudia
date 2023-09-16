package com.example.estudia.activities;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.estudia.R;
import com.example.estudia.enums.PersonalityEnum;
import com.example.estudia.services.PreferencesEstudiaService;

public class Personality extends AppCompatActivity {
    PersonalityEnum personalityEnum;
    PersonalityEnum[] personalities = PersonalityEnum.values();
    ImageView image;
    TextView description;
    Button btnPersonality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality);

        ConstraintLayout constraintLayout = findViewById(R.id.personalityLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        image = (ImageView) findViewById(R.id.personalityImage);
        description = (TextView) findViewById(R.id.personalityDescription);
        btnPersonality = (Button) findViewById(R.id.nextButtonPersonality);

        Intent intent = getIntent();
        String personality = intent.getStringExtra("personality");

        for (int i = 0; i < personalities.length; i++) {
            if (personalities[i].getPersonality().equals(personality)) {
                personalityEnum = personalities[i];
            }
        }

        image.setImageResource(personalityEnum.getImageId());
        description.setText(personalityEnum.getDescriptionId());

        btnPersonality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Activar Vista de programas
                Intent i = new Intent(getApplicationContext(), StudyPrograms.class);
                startActivity(i);
                finish();
            }
        });
    }
}
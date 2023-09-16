package com.example.estudia.activities;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_1;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_2;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.estudia.R;
import com.example.estudia.enums.PersonalityEnum;
import com.example.estudia.services.PreferencesEstudiaService;

public class IntroPersonalities extends AppCompatActivity {

    PreferencesEstudiaService preferencesEstudiaService;
    PersonalityEnum personalityEnum, personalityEnum2, personalityEnum3;
    PersonalityEnum[] personalities = PersonalityEnum.values();
    CardView personalityOneCardView, personalityTwoCardView, personalityThreeCardView;
    TextView textPersonalityOne, textPersonalityTwo, textPersonalityThree;
    ImageView imagePersonalityOne, imagePersonalityTwo, imagePersonalityThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_personalities);

        textPersonalityOne = (TextView) findViewById(R.id.titlePersonalityOne);
        imagePersonalityOne = (ImageView) findViewById(R.id.imagePersonalityOne);
        textPersonalityTwo = (TextView) findViewById(R.id.titlePersonalityTwo);
        imagePersonalityTwo = (ImageView) findViewById(R.id.imagePersonalityTwo);
        textPersonalityThree = (TextView) findViewById(R.id.titlePersonalityThree);
        imagePersonalityThree = (ImageView) findViewById(R.id.imagePersonalityThree);
        personalityOneCardView = (CardView) findViewById(R.id.personalityOne);
        personalityTwoCardView = (CardView) findViewById(R.id.personalityTwo);
        personalityThreeCardView = (CardView) findViewById(R.id.personalityThree);

        this.preferencesEstudiaService = new PreferencesEstudiaService(this);
        String personalityOne = this.preferencesEstudiaService.getAttribute(PERSONALITY_1);
        String personalityTwo = this.preferencesEstudiaService.getAttribute(PERSONALITY_2);
        String personalityThree = this.preferencesEstudiaService.getAttribute(PERSONALITY_3);

        for (PersonalityEnum personality : personalities) {
            if (personality.getPersonality().equals(personalityOne)) {
                personalityEnum = personality;
            }
            if (personality.getPersonality().equals(personalityTwo)) {
                personalityEnum2 = personality;
            }
            if (personality.getPersonality().equals(personalityThree)) {
                personalityEnum3 = personality;
            }
        }

        textPersonalityOne.setText(personalityEnum.getPersonality());
        imagePersonalityOne.setImageResource(personalityEnum.getIcon());
        textPersonalityTwo.setText(personalityEnum2.getPersonality());
        imagePersonalityTwo.setImageResource(personalityEnum2.getIcon());
        textPersonalityThree.setText(personalityEnum3.getPersonality());
        imagePersonalityThree.setImageResource(personalityEnum3.getIcon());

        personalityOneCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Personality.class);
                intent.putExtra("personality", personalityOne);
                startActivity(intent);
            }
        });

        personalityTwoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Personality.class);
                intent.putExtra("personality", personalityTwo);
                startActivity(intent);
            }
        });

        personalityThreeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Personality.class);
                intent.putExtra("personality", personalityThree);
                startActivity(intent);
            }
        });

    }
}
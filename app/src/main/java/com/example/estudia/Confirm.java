package com.example.estudia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.estudia.entities.BasicProfile;
import com.example.estudia.entities.Profile;
import com.example.estudia.facades.CognitoImplementation;
import com.example.estudia.services.ValidationsService;

public class Confirm extends AppCompatActivity {

    Profile profile;
    BasicProfile basicProfile;
    ScrollView scrollView;
    LinearLayout confirmL;
    EditText eCodeConfirm;
    Button confirmButton;
    String code;
    ValidationsService validations;

    //Confirm Service
    CognitoImplementation cognitoImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        //Recepción de datos Activity Register
        profile = (Profile) getIntent().getSerializableExtra("profile");
        basicProfile = (BasicProfile) getIntent().getSerializableExtra("basicProfile");

        //ScrollView Confirm
        scrollView = (ScrollView) findViewById(R.id.scrollViewConfirm);

        //LinearLayout de Confirm
        confirmL = (LinearLayout) findViewById(R.id.linearLayoutCodeConfirm);

        //EditText de Confirm
        eCodeConfirm = (EditText) findViewById(R.id.codeConfirm);

        //Button
        confirmButton = (Button) findViewById(R.id.confirmButton);

        //Validaciones
        validations = new ValidationsService();

        cognitoImplementation = new CognitoImplementation(getApplicationContext());

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validations.Vacio(eCodeConfirm)) {
                    code = eCodeConfirm.getText().toString().trim();
                    Log.i("code:", code);
                    cognitoImplementation.confirmEmail(profile, basicProfile, code);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Ingrese el código de confirmación", Toast.LENGTH_SHORT).show();
                            eCodeConfirm.setError("Campo Requerido");
                            eCodeConfirm.requestFocus();
                            scrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}
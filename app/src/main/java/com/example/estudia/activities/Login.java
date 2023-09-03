package com.example.estudia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estudia.R;
import com.example.estudia.facades.CognitoImplementation;
import com.example.estudia.services.ValidationsService;

public class Login extends AppCompatActivity {

    EditText eEmail, ePassword;
    LinearLayout emailL, passwordL;
    Button btnLogin;
    String email, password;
    ValidationsService validations;
    CognitoImplementation cognitoImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //LinearLayout de Login
        emailL = (LinearLayout) findViewById (R.id.linearLayoutEmailLogin);
        passwordL = (LinearLayout) findViewById (R.id.linearLayoutPasswordLogin);

        //EditText de Login
        eEmail = (EditText) findViewById (R.id.emailLogin);
        ePassword = (EditText) findViewById(R.id.passLogin);

        //Button
        btnLogin = (Button) findViewById(R.id.loginButton);

        //Validaciones
        validations = new ValidationsService();

        //Cognito Service
        cognitoImplementation = new CognitoImplementation(getApplicationContext());

        cognitoImplementation.getCurrentUser();

        final TextView txtSub = (TextView) findViewById(R.id.buttonRegisterLogin);
        txtSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), Register.class);
                intent.putExtra("activity", "login");
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDatos()) {
                    fillData();
                    cognitoImplementation.signIn(email, password);
                }
            }
        });
    }

    public void fillData(){
        email = eEmail.getText().toString().trim();
        password = ePassword.getText().toString().trim();
    }

    public boolean validarDatos() {
        //Validación Email
        String email = eEmail.getText().toString().trim();
        if(validations.Vacio ( eEmail )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese su Email" , Toast.LENGTH_SHORT ).show ();
                    eEmail.setError("Campo Requerido");
                    eEmail.requestFocus();
                }
            });
            return false;
        }
        //Validación Contraseña
        String password = ePassword.getText().toString().trim();
        if(validations.Vacio ( ePassword )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese una Contraseña" , Toast.LENGTH_SHORT ).show ();
                    ePassword.setError("Campo Requerido");
                    ePassword.requestFocus();
                }
            });
            return false;
        }
        return true;
    }
}
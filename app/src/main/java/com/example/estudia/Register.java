package com.example.estudia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.estudia.entities.BasicProfile;
import com.example.estudia.entities.Profile;
import com.example.estudia.facades.CognitoImplementation;
import com.example.estudia.services.ValidationsService;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    String eAge;
    Button btnNext, btnDate;
    EditText eName, eLastName, eEmail, ePassword, ePasswordConfirm, ePhone;
    ScrollView scrollView;
    LinearLayout nameL, lastNameL, emailL, passwordL, passwordConfirmL, phoneL, dateL, stratumL, genderL;
    Spinner spinStratum, spinGender;
    private int year, month, day;
    String name, lastName, email, password, phone, age, stratum, gender;
    String redColor = "#40FF0000";
    ValidationsService validations;
    boolean validacionOK;
    private DatePickerDialog datePickerDialog;

    //Cognito Service
    CognitoImplementation cognitoImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //ScrollView Register
        scrollView = (ScrollView) findViewById(R.id.scrollViewRegister);

        //LinearLayout de Register
        nameL = (LinearLayout) findViewById ( R.id.linearLayoutNameRegister );
        lastNameL = (LinearLayout) findViewById ( R.id.linearLayoutLastNameRegister );
        emailL = (LinearLayout) findViewById ( R.id.linearLayoutEmailRegister );
        passwordL = (LinearLayout) findViewById ( R.id.linearLayoutPasswordRegister );
        passwordConfirmL = (LinearLayout) findViewById ( R.id.linearLayoutPasswordConfirmRegister );
        phoneL = (LinearLayout) findViewById ( R.id.linearLayoutPhoneRegister );
        dateL = (LinearLayout) findViewById ( R.id.linearLayoutBirthDateRegister );
        stratumL = (LinearLayout) findViewById ( R.id.linearLayoutSocialStratumRegister );
        genderL = (LinearLayout) findViewById ( R.id.linearLayoutGenderRegister );

        //EditText de Register
        eName = (EditText) findViewById ( R.id.nameRegister );
        eLastName = (EditText) findViewById ( R.id.lastNameRegister );
        eEmail = (EditText) findViewById ( R.id.emailRegister );
        ePassword = (EditText) findViewById ( R.id.passwordRegister );
        ePasswordConfirm = (EditText) findViewById ( R.id.passwordConfirmRegister );
        ePhone = (EditText) findViewById ( R.id.phoneRegister );
        spinStratum = (Spinner) findViewById(R.id.socialStratumRegister);
        spinGender = (Spinner) findViewById(R.id.genderRegister);

        //Buttons
        btnDate = (Button) findViewById(R.id.birthDateRegister);
        btnNext = (Button) findViewById(R.id.registerNextButton);

        //Validaciones
        validations = new ValidationsService();

        cognitoImplementation = new CognitoImplementation(getApplicationContext());

        initDatePicker();

        //Validar datos
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadB b = new ThreadB();
                b.start();

                synchronized(b){

                    try{
                        System.out.println("Waiting for b to complete...");
                        b.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    if(validacionOK) {
                        fillData ();
                        Profile profile = createProfile();;
                        BasicProfile basicProfile = createBasicProfile();
                        cognitoImplementation.signUp(profile, basicProfile);
                    }
                }
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yearDialog, int monthDialog, int dayDialog) {
                monthDialog = monthDialog + 1;
                year = yearDialog;
                month = monthDialog;
                day = dayDialog;
                String date = makeDateString(dayDialog, monthDialog, yearDialog);
                btnDate.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return day + "/" + getMonthFormat(month) + "/" + year;
    }

    private String getMonthFormat(int month) {
        switch (month){
            case 1:
                return "Ene";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dic";
            default:
                return "Month";
        }
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    class ThreadB extends Thread{
        int total;
        @Override
        public void run(){
            synchronized(this){
                validacionOK = validarDatos ();
                notify();
            }
        }
    }

    private void fillData(){
        name = eName.getText().toString().trim();
        lastName = eLastName.getText().toString().trim();
        email = eEmail.getText().toString().trim();
        password = ePassword.getText().toString().trim();
        phone = ePhone.getText().toString().trim();
        eAge= validations.getAge(year,month,day);
        age = eAge;
        stratum = spinStratum.getSelectedItem().toString().trim();
        gender = spinGender.getSelectedItem().toString().trim();
    }

    private Profile createProfile() {
        Profile user = new Profile(email);
        user.setPassword(password);
        return user;
    }

    private BasicProfile createBasicProfile() {
        return new BasicProfile(email, name, lastName, phone, age, stratum, gender);
    }

    public boolean validarDatos(){
        //Validación Nombres
        if(validations.Vacio ( eName )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese su Nombre" , Toast.LENGTH_SHORT ).show ();
                    eName.setError("Campo Requerido");
                    eName.requestFocus();
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                }
            });
            return false;
        }

        //Validación Apellidos
        if(validations.Vacio ( eLastName )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese sus Apellidos" , Toast.LENGTH_SHORT ).show ();
                    eLastName.setError("Campo Requerido");
                    eLastName.requestFocus();
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                }
            });
            return false;
        }

        //Validación Email
        String email = eEmail.getText().toString().trim();
        if(validations.Vacio ( eEmail )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese su Email" , Toast.LENGTH_SHORT ).show ();
                    eEmail.setError("Campo Requerido");
                    eEmail.requestFocus();
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                }
            });
            return false;
        }

        //Email válido
        if(!validations.isEmail ( email )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    eEmail.setError ( "Campo Requerido" );
                    eEmail.requestFocus ();
                    Toast.makeText ( getApplicationContext () , "Ingrese un Email válido" , Toast.LENGTH_SHORT ).show ();
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
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
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                }
            });
            return false;
        }

        //Validación Contraseña AlfaNumérica
        if(!validations.isPassword ( password )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese una Contraseña Válida (Igual o mayor a 8 carácteres y alfanumérica)" , Toast.LENGTH_SHORT ).show ();
                    ePassword.setError("Campo Requerido");
                    ePassword.requestFocus();
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                }
            });
            return false;
        }

        //Validación Validar Contraseña
        String valPassword = ePasswordConfirm.getText().toString().trim();
        if(validations.Vacio ( ePasswordConfirm )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese Validación de Contraseña" , Toast.LENGTH_SHORT ).show ();
                    ePasswordConfirm.setError("Campo Requerido");
                    ePasswordConfirm.requestFocus();
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                }
            });
            return false;
        }

        //Contraseñas coinciden
        if(!validations.isEquals ( password, valPassword )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Las contraseñas no son iguales" , Toast.LENGTH_SHORT ).show ();
                    ePassword.getBackground ().setColorFilter ( getResources ().getColor ( R.color.tangerine_300 ) , PorterDuff.Mode.SRC_ATOP );
                    passwordL.setBackgroundColor ( Color.parseColor ( redColor ) );
                    ePasswordConfirm.getBackground ().setColorFilter ( getResources ().getColor ( R.color.tangerine_300 ) , PorterDuff.Mode.SRC_ATOP );
                    passwordConfirmL.setBackgroundColor ( Color.parseColor ( redColor ) );
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                }
            });
            return false;
        }

        //Validación Celular
        if(validations.Vacio ( ePhone )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese su Celular" , Toast.LENGTH_SHORT ).show ();
                    ePhone.setError("Campo Requerido");
                    ePhone.requestFocus();
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                }
            });
            return false;
        }

        //Validación Fecha de Nacimiento
        String dateString = btnDate.getText().toString().trim();
        if(validations.isEquals(dateString, "Selecciona tu fecha de nacimiento")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese su Fecha de Nacimiento" , Toast.LENGTH_SHORT ).show ();
                    btnDate.setError("Campo Requerido");
                    btnDate.requestFocus();
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                }
            });
            return false;
        }

        //Validación Estrato
        String stratum = spinStratum.getSelectedItem().toString().trim();
        if (validations.isEquals(stratum, "Selecciona tu estrato")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Ingrese su Estrato", Toast.LENGTH_SHORT).show();
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(ScrollView.FOCUS_UP);
                        }
                    });
                    stratumL.setBackgroundColor(Color.parseColor(redColor));
                }
            });
            return false;
        }

        //Validación Género
        String gender = spinGender.getSelectedItem().toString().trim();
        if(validations.isEquals ( gender, "Selecciona tu género" )){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText ( getApplicationContext () , "Ingrese su Género" , Toast.LENGTH_SHORT ).show ();
                    scrollView.post ( new Runnable () {
                        @Override
                        public void run() {
                            scrollView.fullScroll ( ScrollView.FOCUS_UP );
                        }
                    } );
                    genderL.setBackgroundColor ( Color.parseColor ( redColor ) );
                }
            });
            return false;
        }
        return true;
    }

}
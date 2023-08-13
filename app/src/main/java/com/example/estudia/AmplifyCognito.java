package com.example.estudia;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.estudia.entities.BasicProfile;
import com.example.estudia.entities.Profile;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class AmplifyCognito {
    Context context;

    public AmplifyCognito(Context context) {
        this.context = context;
    }

    public void signUp(Profile userProfile, BasicProfile basicUserProfile) {

        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.name(), basicUserProfile.getName())
                .userAttribute(AuthUserAttributeKey.familyName(), basicUserProfile.getLastName())
                .userAttribute(AuthUserAttributeKey.phoneNumber(), basicUserProfile.getPhone())
                .userAttribute(AuthUserAttributeKey.custom("age"), basicUserProfile.getAge())
                .userAttribute(AuthUserAttributeKey.custom("stratum"), basicUserProfile.getStratum())
                .userAttribute(AuthUserAttributeKey.gender(), basicUserProfile.getGender())
                .build();

//        ArrayList<AuthUserAttribute> attributes = new ArrayList<>();
//        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.name(), basicUserProfile.getName()));
//        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.familyName(), basicUserProfile.getLastName()));
//        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(), basicUserProfile.getPhone()));
//        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.birthdate(), basicUserProfile.getAge()));
//        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.zoneInfo(), basicUserProfile.getStratum()));
//        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.gender(), basicUserProfile.getGender()));

        Amplify.Auth.signUp(userProfile.getEmail(), userProfile.getPassword(), options,
                result -> {
                    Log.i("AuthQuickStart", "Result: " + result.toString());
//                    showToast("Se han registrado los datos básicos del usuario");
                    Intent intent = new Intent(context, Confirm.class);
                    loadConfirmActivity(userProfile, basicUserProfile, intent);
                },
                error -> {
                    Log.e("AuthQuickStart", "Sign up failed", error);
//                    showToast("Falla en el servicio de registro. Intenta nuevamente");
                }
        );
    }

    public void loadConfirmActivity(Profile profile, BasicProfile basicProfile, Intent intent) {
        intent.putExtra("profile", profile);
        intent.putExtra("basicProfile", basicProfile);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void confirmEmail(Profile userProfile, BasicProfile basicUserProfile, String code) {
        Amplify.Auth.confirmSignUp(
                userProfile.getEmail(),
                code,
                result -> {
                    Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                    if (result.isSignUpComplete()) {
//                        showToast("Se ha confirmado tu cuenta");
                        Intent intent = new Intent(context, CustomRegister.class);
                        loadConfirmActivity(userProfile, basicUserProfile, intent);
                    } else {
//                        showToast("Ingrese un código de confirmación válido");
                    }
                },
                error -> {
                    Log.e("AuthQuickstart", error.toString());
//                    showToast("Falla en el servicio de confirmación. Intenta nuevamente");
                }
        );
    }

    public void showToast(String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

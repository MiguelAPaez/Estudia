package com.example.estudia.facades;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.cognito.result.GlobalSignOutError;
import com.amplifyframework.auth.cognito.result.HostedUIError;
import com.amplifyframework.auth.cognito.result.RevokeTokenError;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.estudia.activities.Confirm;
import com.example.estudia.activities.WelcomeIntroSlides;
import com.example.estudia.activities.Login;
import com.example.estudia.activities.MainActivity;
import com.example.estudia.entities.BasicProfile;
import com.example.estudia.entities.Profile;
import com.example.estudia.services.PreferencesEstudiaService;
import com.example.estudia.services.ToastEstudiaService;

import java.util.ArrayList;

public class CognitoImplementation {
    Context context;
    PreferencesEstudiaService preferencesEstudiaService;
    ToastEstudiaService toastEstudiaService;

    public CognitoImplementation(Context context) {
        this.context = context;
        this.preferencesEstudiaService = new PreferencesEstudiaService(this.context);
        this.toastEstudiaService = new ToastEstudiaService(this.context);
    }

    public void signUp(Profile userProfile, BasicProfile basicUserProfile) {

        ArrayList<AuthUserAttribute> attributes = new ArrayList<>();
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.name(), basicUserProfile.getName()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.familyName(), basicUserProfile.getLastName()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(), basicUserProfile.getPhone()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.zoneInfo(), basicUserProfile.getStratum()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.nickname(), basicUserProfile.getAge()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.gender(), basicUserProfile.getGender()));

        Amplify.Auth.signUp(userProfile.getEmail(), userProfile.getPassword(), AuthSignUpOptions.builder().userAttributes(attributes).build(),
                result -> {
                    Log.i("AuthQuickStart", "Result: " + result.toString());
//                    this.toastEstudiaService.showToast("Se han registrado los datos básicos del usuario");
                    Intent intent = new Intent(context, Confirm.class);
                    intent.putExtra("profile", userProfile);
                    intent.putExtra("basicProfile", basicUserProfile);
                    sendToActivity(intent);
                },
                error -> {
                    Log.e("AuthQuickStart", "Sign up failed", error);
//                    this.toastEstudiaService.showToast("Falla en el servicio de registro. Intenta nuevamente");
                }
        );
    }

    public void signIn(String email, String password) {
        Amplify.Auth.signIn(email, password,
                result -> {
                    Log.i("AuthQuickstart", result.isSignedIn() ? "Sign in succeeded" : "Sign in not complete");
                    if(result.isSignedIn()) {
                        userAttributes();
                        Intent intent = new Intent(context, WelcomeIntroSlides.class);
                        sendToActivity(intent);
                    }
                },
                error -> Log.e("AuthQuickstart", error.toString()));
    }

    public void signOut() {
        Amplify.Auth.signOut( signOutResult -> {
            if (signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
                // Sign Out completed fully and without errors.
                Log.i("AuthQuickStart", "Signed out successfully");
                Intent intent = new Intent(context, Login.class);
                sendToActivity(intent);
            } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
                // Sign Out completed with some errors. User is signed out of the device.
                AWSCognitoAuthSignOutResult.PartialSignOut partialSignOutResult =
                        (AWSCognitoAuthSignOutResult.PartialSignOut) signOutResult;

                HostedUIError hostedUIError = partialSignOutResult.getHostedUIError();
                if (hostedUIError != null) {
                    Log.e("AuthQuickStart", "HostedUI Error", hostedUIError.getException());
                    // Optional: Re-launch hostedUIError.getUrl() in a Custom tab to clear Cognito web session.
                }

                GlobalSignOutError globalSignOutError = partialSignOutResult.getGlobalSignOutError();
                if (globalSignOutError != null) {
                    Log.e("AuthQuickStart", "GlobalSignOut Error", globalSignOutError.getException());
                    // Optional: Use escape hatch to retry revocation of globalSignOutError.getAccessToken().
                }

                RevokeTokenError revokeTokenError = partialSignOutResult.getRevokeTokenError();
                if (revokeTokenError != null) {
                    Log.e("AuthQuickStart", "RevokeToken Error", revokeTokenError.getException());
                    // Optional: Use escape hatch to retry revocation of revokeTokenError.getRefreshToken().
                }
            } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
                AWSCognitoAuthSignOutResult.FailedSignOut failedSignOutResult =
                        (AWSCognitoAuthSignOutResult.FailedSignOut) signOutResult;
                // Sign Out failed with an exception, leaving the user signed in.
                Log.e("AuthQuickStart", "Sign out Failed", failedSignOutResult.getException());
            }
        });
    }

    public void confirmEmail(Profile userProfile, BasicProfile basicUserProfile, String code) {
        Amplify.Auth.confirmSignUp(
                userProfile.getEmail(),
                code,
                result -> {
                    Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                    if (result.isSignUpComplete()) {
//                        this.toastEstudiaService.showToast("Se ha confirmado tu cuenta");
                        signIn(userProfile.getEmail(), userProfile.getPassword());
                    } else {
//                        this.toastEstudiaService.showToast("Ingrese un código de confirmación válido");
                    }
                },
                error -> {
                    Log.e("AuthQuickstart", error.toString());
//                    this.toastEstudiaService.showToast("Falla en el servicio de confirmación. Intenta nuevamente");
                }
        );
    }

    public void userAttributes() {
        Amplify.Auth.fetchUserAttributes(
                attributes -> {
                    Log.i("AuthDemo", "User attributes = " + attributes.toString());
                    Object[] elements = attributes.toArray();
                    for (int i = 0; i <= elements.length; i++) {
                        AuthUserAttribute dataElement = (AuthUserAttribute) elements[i];
                        AuthUserAttributeKey key = (AuthUserAttributeKey) dataElement.getKey();
                        this.preferencesEstudiaService.writeAttribute(key.getKeyString(), dataElement.getValue());
                    }
                },
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );
    }

    public void getCurrentUser() {
        try {
            Amplify.Auth.getCurrentUser(
                    result -> {
                        Log.i("AuthQuickstart", "Current user details are:" + result.toString());
                        userAttributes();
                        Intent intent = new Intent(context, MainActivity.class);
                        sendToActivity(intent);
                    },
                    error -> Log.e("AuthQuickstart", "getCurrentUser failed with an exception: " + error)
            );
        } catch (Exception error) {
            Log.e("AuthQuickstart", "unexpected error: " + error);
        }
    }

    public void sendToActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

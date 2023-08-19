package com.example.estudia.facades;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.cognito.result.GlobalSignOutError;
import com.amplifyframework.auth.cognito.result.HostedUIError;
import com.amplifyframework.auth.cognito.result.RevokeTokenError;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.estudia.Confirm;
import com.example.estudia.CustomRegister;
import com.example.estudia.Login;
import com.example.estudia.MainActivity;
import com.example.estudia.entities.BasicProfile;
import com.example.estudia.entities.Profile;

public class CognitoImplementation {
    Context context;

    public CognitoImplementation(Context context) {
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
                    intent.putExtra("profile", userProfile);
                    intent.putExtra("basicProfile", basicUserProfile);
                    sendToActivity(intent);
                },
                error -> {
                    Log.e("AuthQuickStart", "Sign up failed", error);
//                    showToast("Falla en el servicio de registro. Intenta nuevamente");
                }
        );
    }

    public void signIn(String email, String password) {
        Amplify.Auth.signIn(email, password,
                result -> {
                    Log.i("AuthQuickstart", result.isSignedIn() ? "Sign in succeeded" : "Sign in not complete");
                    if(result.isSignedIn()) {
                        Intent intent = new Intent(context, CustomRegister.class);
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
//                        showToast("Se ha confirmado tu cuenta");
                        Intent intent = new Intent(context, Login.class);
                        sendToActivity(intent);
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

    public void userAttributes() {
        Amplify.Auth.fetchUserAttributes(
                attributes -> Log.i("AuthDemo", "User attributes = " + attributes.toString()),
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );
    }

    public void sendToActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void showToast(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context.getApplicationContext());
        // Set Alert Title
        builder.setTitle("Notificación");
        // Set the message show for the Alert time
        builder.setMessage(message);
        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}

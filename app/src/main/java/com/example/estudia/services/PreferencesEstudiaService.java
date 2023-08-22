package com.example.estudia.services;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.SHARED_PREFS;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesEstudiaService {

    SharedPreferences sharedpreferences;

    public PreferencesEstudiaService(Context context) {
        sharedpreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public void writeAttribute(String name, String value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public String getAttribute(String name) {
        return sharedpreferences.getString(name, null);
    }
}

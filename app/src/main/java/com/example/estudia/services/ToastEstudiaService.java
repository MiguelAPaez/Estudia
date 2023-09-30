package com.example.estudia.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class ToastEstudiaService {

    Context context;

    public ToastEstudiaService(Context context) {
        this.context = context;
    }

    public void showToast(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this.context, message, duration);
        toast.show();
    }
}

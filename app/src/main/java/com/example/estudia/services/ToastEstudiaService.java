package com.example.estudia.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ToastEstudiaService {

    Context context;

    public ToastEstudiaService(Context context) {
        this.context = context;
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

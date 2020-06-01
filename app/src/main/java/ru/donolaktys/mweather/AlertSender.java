package ru.donolaktys.mweather;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class AlertSender {
    String errorID;
    String error;
    Context context;

    public AlertSender(Context context, String error, String errorID) {
        this.context = context;
        this.error = error;
        this.errorID = errorID;
        send();
    }

    public AlertSender(Context context, String error) {
        this.context = context;
        this.error = error;
        this.errorID = "";
        send();
    }

    public void send() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error code :" + errorID)
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton(R.string.err_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}

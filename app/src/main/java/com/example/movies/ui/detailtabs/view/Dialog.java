package com.example.movies.ui.detailtabs.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {


    private String[] list = {"Öncelikli", "İsim", "Karakter"};

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Sırala")
                .setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("sorting", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("key", which);
                        editor.apply();

                        switch (which) {
                            case 0:
                                sendBackResult(which);
                            case 1:
                                sendBackResult(which);
                            case 2:
                                sendBackResult(which);
                        }


                    }
                });
        return builder.create();
    }


    public void sendBackResult(int alf) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        DialogListener listener = (DialogListener) getTargetFragment();
        listener.onFinishDialog(alf);
        dismiss();
    }

    public interface DialogListener {
        void onFinishDialog(int inputText);
    }
}

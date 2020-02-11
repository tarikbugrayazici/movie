package com.example.movies.core.helper;

import android.annotation.SuppressLint;
import android.view.View;

@SuppressLint("SimpleDateFormat")
public class ShowMoreHelper {
    private static boolean isCharacterCountOfOverviewOverflowed;

   /* public static String formatDate(String bio, String biography) {
        isCharacterCountOfOverviewOverflowed = bio.length() > 140;
        if (isCharacterCountOfOverviewOverflowed) {
            biography.setText(bio.substring(0, 137) + "...");
        } else {
            biography.setText(bio);
        }
        biography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCharacterCountOfOverviewOverflowed = !isCharacterCountOfOverviewOverflowed;
                if (isCharacterCountOfOverviewOverflowed) {
                    biography.setText(bio.substring(0, 137) + "...");
                } else {
                    biography.setText(bio);
                }
            }
        });
    }*/
}

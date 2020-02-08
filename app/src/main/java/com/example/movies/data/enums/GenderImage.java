package com.example.movies.data.enums;

import com.example.movies.R;

public enum GenderImage {
    MALE(R.drawable.male),
    FEMALE(R.drawable.male);

    private int gender;
    GenderImage(int gender) {
        this.gender = gender;
    }
    public int getGenderImage() {
        return gender;
    }
}


package com.example.movies.data.enums;

public enum Sorting {
    BY_RECOMMENDED(0),
    BY_NAME(1),
    BY_CHARACTER(2);

    private int value;

    Sorting(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

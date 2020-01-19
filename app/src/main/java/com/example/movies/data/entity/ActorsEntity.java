package com.example.movies.data.entity;

import java.util.ArrayList;

public class ActorsEntity {
    private String birthday;
    private String known_for_department;
    private String deadthday;
    private int id;
    private String name;
    private ArrayList<String> also_known_as;
    private int gender;
    private String biography;
    private Number popularity;
    private String place_of_birth;
    private String profile_path;
    private boolean adult;
    private String imdb_id;
    private String homepage;

    public String getBirthday() {
        return birthday;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public String getDeadthday() {
        return deadthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAlso_known_as() {
        return also_known_as;
    }

    public int getGender() {
        return gender;
    }

    public String getBiography() {
        return biography;
    }

    public Number getPopularity() {
        return popularity;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getHomepage() {
        return homepage;
    }
}

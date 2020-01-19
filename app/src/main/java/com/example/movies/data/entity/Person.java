package com.example.movies.data.entity;

import java.util.ArrayList;

public class Person {
    private boolean adult;
    private int gender;
    private String name;
    private int id;
    ArrayList<KnownFor> knownFors;
    private String knownForDeparment;
    private String profile_path;
    private Number popularty;
    private String mediaType;

    public boolean isAdult() {
        return adult;
    }

    public int getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<KnownFor> getKnownFors() {
        return knownFors;
    }

    public String getKnownForDeparment() {
        return knownForDeparment;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public Number getPopularty() {
        return popularty;
    }

    public String getMediaType() {
        return mediaType;
    }
}

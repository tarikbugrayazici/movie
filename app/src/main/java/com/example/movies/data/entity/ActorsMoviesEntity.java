package com.example.movies.data.entity;

import java.util.ArrayList;

public class ActorsMoviesEntity {
    private ArrayList<CastDetail> cast;
    private ArrayList<CrewDetail> crew;
    private int id;

    public ArrayList<CastDetail> getCast() {
        return cast;
    }

    public ArrayList<CrewDetail> getCrew() {
        return crew;
    }

    public int getId() {
        return id;
    }
}

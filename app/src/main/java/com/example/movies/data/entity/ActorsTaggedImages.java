package com.example.movies.data.entity;

import java.util.ArrayList;

public class ActorsTaggedImages {
    private int id;
    private int page;
    private int total_results;
    private ArrayList<ActorsResult> results;
    private int total_pages;

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public ArrayList<ActorsResult> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }
}

package com.example.movies.data.entity;

import java.util.ArrayList;

public class BaseEntityTv {
    private int page;
    private int total_results;
    private int total_pages;
    private ArrayList<Tv> results;

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public ArrayList<Tv> getResults() {
        return results;
    }
}

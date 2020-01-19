package com.example.movies.data.entity;

import java.util.ArrayList;

public class SimilarMovies {
    private int page;
    private ArrayList<SimilarMovie> results;
    private int total_pages;
    private int total_results;

    public int getPage() {
        return page;
    }

    public ArrayList<SimilarMovie> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }
}

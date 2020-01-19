package com.example.movies.data.entity;

import java.util.ArrayList;

public class SearchMovieBaseEntity {
    private int page;
    private int totalresults;
    private int totalPages;
    private ArrayList<SearchMovie> results;

    public int getPage() {
        return page;
    }

    public int getTotalresults() {
        return totalresults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public ArrayList<SearchMovie> getResults() {
        return results;
    }
}

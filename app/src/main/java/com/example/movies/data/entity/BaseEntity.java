package com.example.movies.data.entity;

import java.util.ArrayList;

/**
 * Created by Tarik on 16.11.2019.
 */

public class BaseEntity {
    private int page;
    private int total_results;
    private int total_pages;
    private ArrayList<Movie> results;

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }
}

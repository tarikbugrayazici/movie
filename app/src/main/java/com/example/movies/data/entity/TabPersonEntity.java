package com.example.movies.data.entity;

import com.example.movies.data.entity.Person;

import java.util.ArrayList;

public class TabPersonEntity {
    private int page;
    private ArrayList<Person> results;
    private int total_pages;
    private int total_results;

    public int getPage() {
        return page;
    }

    public ArrayList<Person> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }
}

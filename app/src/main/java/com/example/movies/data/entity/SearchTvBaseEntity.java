package com.example.movies.data.entity;

import java.util.ArrayList;

public class SearchTvBaseEntity {
    private int page;
    private int totalresults;
    private int totalPages;
    private ArrayList<SearchTv> results;

    public int getPage() {
        return page;
    }

    public int getTotalresults() {
        return totalresults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public ArrayList<SearchTv> getResults() {
        return results;
    }
}

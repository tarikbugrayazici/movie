package com.example.movies.data.entity;

import java.util.ArrayList;

public class SearchTv {
    private Number popularity;
    private int id;
    private String name;
    private int vote_count;
    private Number vote_average;
    private String first_air_date;
    private String original_name;
    private String original_language;
    private ArrayList<Integer> genreids;
    private String backdrop_path;
    private String overview;
    private String poster_path;
    private ArrayList<String> origin_country;

    public Number getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVote_count() {
        return vote_count;
    }

    public Number getVote_average() {
        return vote_average;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public ArrayList<Integer> getGenreids() {
        return genreids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public ArrayList<String> getOrigin_country() {
        return origin_country;
    }
}

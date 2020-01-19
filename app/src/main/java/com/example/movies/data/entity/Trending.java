package com.example.movies.data.entity;

import java.util.ArrayList;

public class Trending {
    private int id;
    private boolean video;
    private int vote_count;
    private Number vote_average;
    private String title;
    private String release_date;
    private String original_language;
    private String orginal_title;
    private ArrayList<Integer> genre_ids;
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String poster_path;
    private Number popularty;
    private String media_type;

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public int getVote_count() {
        return vote_count;
    }

    public Number getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOrginal_title() {
        return orginal_title;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Number getPopularty() {
        return popularty;
    }

    public String getMedia_type() {
        return media_type;
    }
}

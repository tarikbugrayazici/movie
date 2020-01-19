package com.example.movies.data.entity;

import java.util.ArrayList;

public class CrewDetail {
    private int id;
    private String department;
    private String original_language;
    private String original_title;
    private String job;
    private String overview;
    private int vote_count;
    private boolean video;
    private boolean adult;
    private String poster_path;
    private String backdrop_path;
    private ArrayList<Integer> genre_ids;
    private String title;
    private Number popularity;
    private Number vote_average;
    private String release_date;
    private String credit_id;

    public boolean isAdult() {
        return adult;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getJob() {
        return job;
    }

    public String getOverview() {
        return overview;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public Number getPopularity() {
        return popularity;
    }

    public Number getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getCredit_id() {
        return credit_id;
    }
}

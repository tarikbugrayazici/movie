package com.example.movies.data.entity;

import java.util.ArrayList;

public class Media {

    private Float popularity;
    private Integer vote_count;
    private Boolean video;
    private String poster_path;
    private Integer id;
    private Boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private ArrayList<Integer> genre_ids = null;
    private String title;
    private Float vote_average;
    private String overview;
    private String release_date;

    public Float getPopularity() {
        return popularity;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }
}

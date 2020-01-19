package com.example.movies.data.entity;

import java.util.ArrayList;

public class CastDetail {
    private String character;
    private String credit_id;
    private String release_date;
    private int vote_count;
    private boolean video;
    private boolean adult;
    private Number vote_average;
    private String title;
    private ArrayList<Integer> genre_ids;
    private String original_language;
    private String original_title;
    private Number popularity;
    private int id;
    private String bacdrop_path;
    private String overview;
    private String poster_path;

    public String getCharacter() {
        return character;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public boolean isAdult() {
        return adult;
    }

    public Number getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public Number getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
    }

    public String getBacdrop_path() {
        return bacdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
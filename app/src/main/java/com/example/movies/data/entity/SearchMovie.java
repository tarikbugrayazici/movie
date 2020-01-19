package com.example.movies.data.entity;

import java.util.ArrayList;

public class SearchMovie {
    private Number popularty;
    private int id;
    private boolean video;
    private int voteCount;
    private int voteAverage;
    private String title;
    private String releaseDate;
    private String originalLanguage;
    private String originalTitle;
    private ArrayList<Integer> genreids;
    private String backdropPath;
    private boolean adult;
    private String overview;
    private String poster_path;

    public Number getPopularty() {
        return popularty;
    }

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public ArrayList<Integer> getGenreids() {
        return genreids;
    }

    public String getBackdropPath() {
        return backdropPath;
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
}

package com.example.movies.data.entity;

import java.util.ArrayList;

public class DetailInfo {
    private boolean adult;
    private String bacdrop_path;
    private Object belongs_to_collection;
    private int budget;
    private ArrayList<Genres> genres;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private Number popularity;
    private String poster_path;
    private ArrayList<ProductionCompanies> production_companies;
    private ArrayList<ProductionCountries> production_countries;
    private String release_date;
    private int revenue;
    private int runtime;
    private ArrayList<SpokenLanguages> spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private Number vote_average;
    private int vote_count;

    public boolean isAdult() {
        return adult;
    }

    public String getBacdrop_path() {
        return bacdrop_path;
    }

    public Object getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public int getBudget() {
        return budget;
    }

    public ArrayList<Genres> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public Number getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public ArrayList<ProductionCompanies> getProduction_companies() {
        return production_companies;
    }

    public ArrayList<ProductionCountries> getProduction_countries() {
        return production_countries;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public ArrayList<SpokenLanguages> getSpoken_languages() {
        return spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public Number getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }
}

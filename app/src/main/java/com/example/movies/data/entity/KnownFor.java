package com.example.movies.data.entity;

import java.util.ArrayList;

public class KnownFor {
   private String originalName;
   private int id;
   private String name;
   private int vote_count;
   private Number vote_average;
   private String firstAirDate;
   private String posterPath;
   private ArrayList<Integer> genreids;
   private String originalLanguage;
   private String backdropPath;
   private String overview;
   private ArrayList<String> originCountry;
   private Number popularty;
   private String mediaType;

    public String getOriginalName() {
        return originalName;
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

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public ArrayList<Integer> getGenreids() {
        return genreids;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public ArrayList<String> getOriginCountry() {
        return originCountry;
    }

    public Number getPopularty() {
        return popularty;
    }

    public String getMediaType() {
        return mediaType;
    }
}

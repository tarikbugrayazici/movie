package com.example.movies.data.entity;

public class Backdrops {

    private Number aspect_ratio;
    private String file_path;
    private int height;
    private String iso_639_1;
    private double vote_average;
    private int vote_count;
    private int width;

    public Number getAspect_ratio() {
        return aspect_ratio;
    }

    public String getFile_path() {
        return file_path;
    }

    public int getHeight() {
        return height;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public double getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public int getWidth() {
        return width;
    }
}

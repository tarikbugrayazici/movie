package com.example.movies.data.entity;


public class ActorsResult {
    private Number aspect_ratio;
    private String file_path;
    private int height;
    private String iso_639_1;
    private Number vote_average;
    private int vote_count;
    private int widht;
    private String image_type;
    private String media_type;
    private Number popularity;
    private Media media;

    public Media getMedia() {
        return media;
    }

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

    public Number getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public int getWidht() {
        return widht;
    }

    public String getImage_type() {
        return image_type;
    }

    public String getMedia_type() {
        return media_type;
    }

    public Number getPopularity() {
        return popularity;
    }
}

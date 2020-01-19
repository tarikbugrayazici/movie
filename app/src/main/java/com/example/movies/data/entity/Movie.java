package com.example.movies.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Tarik on 16.11.2019.
 */

public class Movie implements Parcelable {
    private Number popularity;
    private int vote_count;
    private boolean video;
    private String poster_path;
    private int id;
    private boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private ArrayList<Integer> genre_ids;
    private String title;
    private Number vote_average;
    private String overview;
    private String release_date;

    public Number getPopularity() {
        return popularity;
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

    public int getId() {
        return id;
    }

    public boolean isAdult() {
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

    public Number getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.popularity);
        dest.writeInt(this.vote_count);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeString(this.poster_path);
        dest.writeInt(this.id);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeList(this.genre_ids);
        dest.writeString(this.title);
        dest.writeSerializable(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.popularity = (Number) in.readSerializable();
        this.vote_count = in.readInt();
        this.video = in.readByte() != 0;
        this.poster_path = in.readString();
        this.id = in.readInt();
        this.adult = in.readByte() != 0;
        this.backdrop_path = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.title = in.readString();
        this.vote_average = (Number) in.readSerializable();
        this.overview = in.readString();
        this.release_date = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}

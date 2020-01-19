package com.example.movies.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Tv implements Parcelable {
    private String original_name;
    private ArrayList<Integer> genre_ids;
    private String name;
    private Number popularty;
    private ArrayList<String> origin_country;
    private int vote_count;
    private String first_air_date;
    private String backdrop_path;
    private String original_language;
    private int id;
    private Number vote_average;
    private String overview;
    private String poster_path;

    public String getOriginal_name() {
        return original_name;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getName() {
        return name;
    }

    public Number getPopularty() {
        return popularty;
    }

    public ArrayList<String> getOrigin_country() {
        return origin_country;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public int getId() {
        return id;
    }

    public Number    getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original_name);
        dest.writeList(this.genre_ids);
        dest.writeString(this.name);
        dest.writeSerializable(this.popularty);
        dest.writeStringList(this.origin_country);
        dest.writeInt(this.vote_count);
        dest.writeString(this.first_air_date);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.original_language);
        dest.writeInt(this.id);
        dest.writeSerializable(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
    }

    public Tv() {
    }

    protected Tv(Parcel in) {
        this.original_name = in.readString();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.name = in.readString();
        this.popularty = (Number) in.readSerializable();
        this.origin_country = in.createStringArrayList();
        this.vote_count = in.readInt();
        this.first_air_date = in.readString();
        this.backdrop_path = in.readString();
        this.original_language = in.readString();
        this.id = in.readInt();
        this.vote_average = (Number) in.readSerializable();
        this.overview = in.readString();
        this.poster_path = in.readString();
    }

    public static final Parcelable.Creator<Tv> CREATOR = new Parcelable.Creator<Tv>() {
        @Override
        public Tv createFromParcel(Parcel source) {
            return new Tv(source);
        }

        @Override
        public Tv[] newArray(int size) {
            return new Tv[size];
        }
    };
}

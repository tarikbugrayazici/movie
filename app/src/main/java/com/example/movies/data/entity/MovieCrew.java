package com.example.movies.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MovieCrew implements Parcelable {
    private int id;
    private ArrayList<Cast> cast;
    private ArrayList<Crew> crew;

    public int getId() {
        return id;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public ArrayList<Crew> getCrew() {
        return crew;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeList(this.cast);
        dest.writeList(this.crew);
    }

    public MovieCrew() {
    }

    protected MovieCrew(Parcel in) {
        this.id = in.readInt();
        this.cast = new ArrayList<Cast>();
        in.readList(this.cast, Cast.class.getClassLoader());
        this.crew = new ArrayList<Crew>();
        in.readList(this.crew, Crew.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieCrew> CREATOR = new Parcelable.Creator<MovieCrew>() {
        @Override
        public MovieCrew createFromParcel(Parcel source) {
            return new MovieCrew(source);
        }

        @Override
        public MovieCrew[] newArray(int size) {
            return new MovieCrew[size];
        }
    };
}

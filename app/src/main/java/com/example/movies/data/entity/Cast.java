package com.example.movies.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Cast implements Parcelable {
    private int cast_id;
    private String character;
    private String credit_id;
    private int gender;
    private int id;
    private String name;
    private int order;
    private String profile_path;

    public int getCast_id() {
        return cast_id;
    }

    public String getCharacter() {
        return character;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public String getProfile_path() {
        return profile_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cast_id);
        dest.writeString(this.character);
        dest.writeString(this.credit_id);
        dest.writeInt(this.gender);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.order);
        dest.writeString(this.profile_path);
    }

    public Cast() {
    }

    protected Cast(Parcel in) {
        this.cast_id = in.readInt();
        this.character = in.readString();
        this.credit_id = in.readString();
        this.gender = in.readInt();
        this.id = in.readInt();
        this.name = in.readString();
        this.order = in.readInt();
        this.profile_path = in.readString();
    }

    public static final Parcelable.Creator<Cast> CREATOR = new Parcelable.Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel source) {
            return new Cast(source);
        }

        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };
}

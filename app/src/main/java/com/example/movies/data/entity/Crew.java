package com.example.movies.data.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class Crew implements Parcelable {
    private String credit_id;
    private String department;
    private int gender;
    private int id;
    private String job;
    private String name;
    private String profile_path;

    public String getCredit_id() {
        return credit_id;
    }

    public String getDepartment() {
        return department;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
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
        dest.writeString(this.credit_id);
        dest.writeString(this.department);
        dest.writeInt(this.gender);
        dest.writeInt(this.id);
        dest.writeString(this.job);
        dest.writeString(this.name);
        dest.writeString(this.profile_path);
    }

    public Crew() {
    }

    protected Crew(Parcel in) {
        this.credit_id = in.readString();
        this.department = in.readString();
        this.gender = in.readInt();
        this.id = in.readInt();
        this.job = in.readString();
        this.name = in.readString();
        this.profile_path = in.readString();
    }

    public static final Parcelable.Creator<Crew> CREATOR = new Parcelable.Creator<Crew>() {
        @Override
        public Crew createFromParcel(Parcel source) {
            return new Crew(source);
        }

        @Override
        public Crew[] newArray(int size) {
            return new Crew[size];
        }
    };
}

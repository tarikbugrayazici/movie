package com.example.movies.data.entity

import android.os.Parcel
import android.os.Parcelable

import java.util.ArrayList

/**
 * Created by Tarik on 16.11.2019.
 */

class Movie : Parcelable {
    val popularity: Number
    val vote_count: Int
    val isVideo: Boolean
    val poster_path: String?
    val id: Int
    val isAdult: Boolean
    val backdrop_path: String?
    val original_language: String?
    val original_title: String?
    val genre_ids: ArrayList<Int>
    val title: String?
    val vote_average: Number
    val overview: String?
    val release_date: String?

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeSerializable(this.popularity)
        dest.writeInt(this.vote_count)
        dest.writeByte(if (this.isVideo) 1.toByte() else 0.toByte())
        dest.writeString(this.poster_path)
        dest.writeInt(this.id)
        dest.writeByte(if (this.isAdult) 1.toByte() else 0.toByte())
        dest.writeString(this.backdrop_path)
        dest.writeString(this.original_language)
        dest.writeString(this.original_title)
        dest.writeList(this.genre_ids)
        dest.writeString(this.title)
        dest.writeSerializable(this.vote_average)
        dest.writeString(this.overview)
        dest.writeString(this.release_date)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.popularity = `in`.readSerializable() as Number
        this.vote_count = `in`.readInt()
        this.isVideo = `in`.readByte().toInt() != 0
        this.poster_path = `in`.readString()
        this.id = `in`.readInt()
        this.isAdult = `in`.readByte().toInt() != 0
        this.backdrop_path = `in`.readString()
        this.original_language = `in`.readString()
        this.original_title = `in`.readString()
        this.genre_ids = ArrayList()
        `in`.readList(this.genre_ids, Int::class.java.classLoader)
        this.title = `in`.readString()
        this.vote_average = `in`.readSerializable() as Number
        this.overview = `in`.readString()
        this.release_date = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie {
                return Movie(source)
            }

            override fun newArray(size: Int): Array<Movie> {
                return arrayOfNulls(size)
            }
        }
    }
}

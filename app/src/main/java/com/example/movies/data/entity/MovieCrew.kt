package com.example.movies.data.entity

import android.os.Parcel
import android.os.Parcelable

import java.util.ArrayList

class MovieCrew : Parcelable {
    val id: Int
    val cast: ArrayList<Cast>
    val crew: ArrayList<Crew>

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeList(this.cast)
        dest.writeList(this.crew)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        this.cast = ArrayList()
        `in`.readList(this.cast, Cast::class.java.classLoader)
        this.crew = ArrayList()
        `in`.readList(this.crew, Crew::class.java.classLoader)
    }

    companion object {

        val CREATOR: Parcelable.Creator<MovieCrew> = object : Parcelable.Creator<MovieCrew> {
            override fun createFromParcel(source: Parcel): MovieCrew {
                return MovieCrew(source)
            }

            override fun newArray(size: Int): Array<MovieCrew> {
                return arrayOfNulls(size)
            }
        }
    }
}

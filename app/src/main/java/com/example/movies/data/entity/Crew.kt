package com.example.movies.data.entity


import android.os.Parcel
import android.os.Parcelable

class Crew : Parcelable {
    val credit_id: String?
    val department: String?
    val gender: Int
    val id: Int
    val job: String?
    val name: String?
    val profile_path: String?

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.credit_id)
        dest.writeString(this.department)
        dest.writeInt(this.gender)
        dest.writeInt(this.id)
        dest.writeString(this.job)
        dest.writeString(this.name)
        dest.writeString(this.profile_path)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.credit_id = `in`.readString()
        this.department = `in`.readString()
        this.gender = `in`.readInt()
        this.id = `in`.readInt()
        this.job = `in`.readString()
        this.name = `in`.readString()
        this.profile_path = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<Crew> = object : Parcelable.Creator<Crew> {
            override fun createFromParcel(source: Parcel): Crew {
                return Crew(source)
            }

            override fun newArray(size: Int): Array<Crew> {
                return arrayOfNulls(size)
            }
        }
    }
}

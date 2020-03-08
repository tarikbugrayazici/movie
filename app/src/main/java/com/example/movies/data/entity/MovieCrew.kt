package com.example.movies.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class MovieCrew(val id: Int? = null,
                     val cast: ArrayList<Cast>,
                     val crew: ArrayList<Crew>) : Parcelable
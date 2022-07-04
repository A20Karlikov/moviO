package com.io.moviO.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val name: String,
    val poster: String,
    val year: String,
    val gerne: String,
    val cast: String,
    val overview: String
) : Parcelable
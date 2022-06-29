package com.io.moviO

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val poster: Int,
    val year: String,
    val gerne: String,
    val cast: String,
    val overview: String
) : Parcelable
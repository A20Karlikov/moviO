package com.io.moviO.data.modelMovieList

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieForList(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val imageUrl: String
) : Parcelable

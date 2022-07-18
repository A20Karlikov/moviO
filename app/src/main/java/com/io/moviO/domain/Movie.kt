package com.io.moviO.domain

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val imageUrl: String,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    val overview: String? = null
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}
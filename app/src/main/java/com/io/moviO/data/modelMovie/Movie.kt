package com.io.moviO.data.modelMovie

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val imageUrl: String,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("genres")
    val genres: List<Genres>?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    val overview: String?
)
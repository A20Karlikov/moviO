package com.io.moviO.data.modelMovieList

import com.google.gson.annotations.SerializedName

data class MovieResources(
    @SerializedName("poster_path")
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genres: List<Int>,
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val popularity: Number,
    @SerializedName("vote_count")
    val vote_count: Int,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double
)
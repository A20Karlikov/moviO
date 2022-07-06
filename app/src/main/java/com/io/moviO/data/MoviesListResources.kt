package com.io.moviO.data

import com.google.gson.annotations.SerializedName
import com.io.moviO.data.modelMovieList.MovieResources

data class MoviesListResources(
    val page: Int,
    @SerializedName("results")
    val moviesResources: List<MovieResources>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)

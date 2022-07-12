package com.io.moviO.data

import com.io.moviO.common.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/top_rated?api_key=${Constants.API_KEY}")
    suspend fun getMovies(): MoviesListResources

    // @POST()
    // fun uploadMovie(): Unit

    @GET("movie/{id}?api_key=${Constants.API_KEY}&language=en-US")
    suspend fun getMovieById(@Path("id") id: Int): MovieByIdResources

    @GET("search/movie?api_key=${Constants.API_KEY}")
    suspend fun searchMovie(@Query("query") query: String): MoviesListResources

    @GET("search/movie?api_key=${Constants.API_KEY}")
    suspend fun latestMovies(
        @Query("query") query: String,
        @Query("primary_release_year") year: Int?
    ): MoviesListResources

}
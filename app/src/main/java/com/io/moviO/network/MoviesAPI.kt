package com.io.moviO.network

import com.io.moviO.common.Constants
import com.io.moviO.data.MoviesListResources
import com.io.moviO.data.modelMovie.MovieByIdResources
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesAPI {

    @GET("/3/movie/top_rated?api_key=${Constants.API_KEY}")
    suspend fun getMovies(): MoviesListResources

    // @POST()
    // fun uploadMovie(): Unit

    @GET("/3/movie/{id}?api_key=${Constants.API_KEY}&language=en-US")
    suspend fun getMovieById(@Path("id") id: Int): MovieByIdResources

}
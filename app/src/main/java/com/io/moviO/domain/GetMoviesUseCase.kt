package com.io.moviO.domain

import com.io.moviO.data.Movie
import com.io.moviO.data.MoviesRepository

class GetMoviesUseCase {
    private val moviesRepo = MoviesRepository

    fun getMovies(): List<Movie> = moviesRepo.getMovies()

}
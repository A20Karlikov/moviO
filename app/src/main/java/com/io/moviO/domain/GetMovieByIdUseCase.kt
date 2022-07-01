package com.io.moviO.domain

import com.io.moviO.data.Movie
import com.io.moviO.data.MoviesRepository

class GetMovieByIdUseCase {

    private val moviesRepo = MoviesRepository

    fun getMovieById(id: String): Movie = moviesRepo.getMovies().first { movie -> movie.id == id }
}
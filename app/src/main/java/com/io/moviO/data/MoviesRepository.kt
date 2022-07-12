package com.io.moviO.data

import com.io.moviO.common.Constants
import com.io.moviO.domain.Movie
import com.io.moviO.network.RetrofitInstance

object MoviesRepository {

    private val moviesByYear: MutableMap<Int, List<Movie>> = mutableMapOf()

    suspend fun getMovies(): List<Movie> = RetrofitInstance.api.getMovies().toDomain()

    suspend fun getMovieById(id: Int): Movie = RetrofitInstance.api.getMovieById(id).toDomain()

    suspend fun searchMovie(query: String): List<Movie> =
        RetrofitInstance.api.searchMovie(query).toDomain()

    suspend fun latestMovies(query: String, year: Int): List<Movie> {
        return if (moviesByYear.containsKey(year)) {
            moviesByYear.getValue(year)
        } else {
            val movies = RetrofitInstance.api.latestMovies(query, year).toDomain()
            moviesByYear.put(year, movies)
            movies
        }
    }
}

private fun MovieByIdResources.toDomain(): Movie = Movie(
    this.id,
    this.title,
    "${Constants.IMAGE_URL_START_PART}${this.posterPath}",
    this.releaseDate,
    this.genres.map { genre -> Movie.Genre(genre.id, genre.name) },
    this.voteAverage,
    this.overview
)


private fun MoviesListResources.toDomain(): List<Movie> =
    this.moviesResources.map {
        Movie(
            it.id,
            it.title,
            "${Constants.IMAGE_URL_START_PART}${it.posterPath}"
        )
    }





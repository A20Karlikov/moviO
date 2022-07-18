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

    suspend fun latestMovies(query: Unit, year: Int): List<Movie> {
        return if (moviesByYear.containsKey(year)) {
            moviesByYear.getValue(year)
        } else {
            val movies = RetrofitInstance.api.latestMovies(year = year).toDomain()
            moviesByYear.put(year, movies)
            movies
        }
    }
}

private fun MovieByIdResources.toDomain(): Movie = Movie(
    id = this.id,
    title = this.title,
    imageUrl = this.posterPath?.run { "${Constants.IMAGE_URL_START_PART}$this" }
        ?: Constants.NO_IMAGE_URL,
    releaseDate = this.releaseDate,
    genres = this.genres.map { genre -> Movie.Genre(genre.id, genre.name) },
    voteAverage = this.voteAverage,
    overview = this.overview
)


private fun MoviesListResources.toDomain(): List<Movie> =
    this.moviesResources.map {
        Movie(
            id = it.id,
            title = it.title,
            imageUrl = it.posterPath?.run { "${Constants.IMAGE_URL_START_PART}$this" }
                ?: Constants.NO_IMAGE_URL

        )
    }





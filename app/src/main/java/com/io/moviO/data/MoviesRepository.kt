package com.io.moviO.data

import com.io.moviO.common.Constants
import com.io.moviO.domain.Movie
import com.io.moviO.network.RetrofitInstance

object MoviesRepository {

    suspend fun getMovies(): List<Movie> = RetrofitInstance.api.getMovies().toDomain()

    suspend fun getMovieById(id: Int): Movie = RetrofitInstance.api.getMovieById(id).toDomain()
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




package com.io.moviO.data

import com.io.moviO.data.modelMovie.Movie
import com.io.moviO.data.modelMovie.MovieByIdResources
import com.io.moviO.data.modelMovieList.MovieForList
import com.io.moviO.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MoviesRepository {

    suspend fun getMovies(): List<MovieForList> =
        withContext(Dispatchers.IO) {
            RetrofitInstance.api.getMovies().toDomain()
        }

    suspend fun getMovieById(id: Int): Movie =
        withContext(Dispatchers.IO) {
            RetrofitInstance.api.getMovieById(id).toDomain()
        }
}

private fun MovieByIdResources.toDomain(): Movie = Movie(
    this.id,
    this.title,
    this.posterPath!!,
    this.releaseDate,
    this.genres,
    this.voteAverage,
    this.overview!!
)


private fun MoviesListResources.toDomain(): List<MovieForList> {
    val movies = mutableListOf<MovieForList>()
    for (movie in this.moviesResources) {
        movies.add(MovieForList(movie.id, movie.title, movie.posterPath))
    }
    return movies
}



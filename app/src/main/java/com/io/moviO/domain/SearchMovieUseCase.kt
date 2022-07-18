package com.io.moviO.domain

import android.util.Log
import com.io.moviO.data.MoviesRepository

class SearchMovieUseCase(private val movieRepo: MoviesRepository = MoviesRepository) :
    UseCase<String, DataResult<List<Movie>>> {

    override suspend fun execute(query: String): DataResult<List<Movie>> {
        return try {
            DataResult.Success(movieRepo.searchMovie(query))
        } catch (ex: Exception) {
            Log.i("error", ex.message.toString())
            DataResult.Fail(ex)
        }
    }
}
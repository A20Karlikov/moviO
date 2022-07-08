package com.io.moviO.searchMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.moviO.domain.DataResult
import com.io.moviO.domain.GetLatestMoviesUseCase
import com.io.moviO.domain.Movie
import com.io.moviO.domain.SearchMovieUseCase
import kotlinx.coroutines.launch

class SearchMovieViewModel : ViewModel() {

    private var _movies = MutableLiveData<DataResult<List<Movie>>>()
    val movies: LiveData<DataResult<List<Movie>>> = _movies

    private val useCase = SearchMovieUseCase()

    fun searchMovie(query: String) {
        viewModelScope.launch {
            val result = useCase.execute(query)
            _movies.value = result
        }
    }

    fun getLatestMovies() {
        viewModelScope.launch {
            val result = GetLatestMoviesUseCase().execute(Unit)
            _movies.value = result
        }
    }
}
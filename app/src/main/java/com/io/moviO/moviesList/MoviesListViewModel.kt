package com.io.moviO.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.moviO.data.Movie
import com.io.moviO.domain.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesListViewModel : ViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val useCase = GetMoviesUseCase()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = useCase.getMovies()
            withContext(Dispatchers.Main) {
                _movies.value = result
            }
        }
    }
}
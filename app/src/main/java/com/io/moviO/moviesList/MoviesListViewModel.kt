package com.io.moviO.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.moviO.data.DataResult
import com.io.moviO.data.modelMovieList.MovieForList
import com.io.moviO.domain.GetMoviesUseCase
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {

    private var _movies = MutableLiveData<DataResult<List<MovieForList>>>()
    val movies: LiveData<DataResult<List<MovieForList>>> = _movies

    private val useCase = GetMoviesUseCase()

    init {
        viewModelScope.launch {
            val result = useCase.execute(Unit)
            _movies.value = result
        }
    }
}
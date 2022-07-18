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

    private var _movies = MutableLiveData<ViewState>()
    val movies: LiveData<ViewState> = _movies

    private val useCase = SearchMovieUseCase()
    private var lastSearch: String? = null

    init {
        getLatestMovies()
    }

    fun searchMovie(query: String) {
        if (lastSearch == query) return

        lastSearch = query
        viewModelScope.launch {
            _movies.value = ViewState.Loading
            when (val result = useCase.execute(query)) {
                is DataResult.Success -> {
                    if (result.value.isEmpty()) {
                        _movies.value = ViewState.NoResults
                    } else {
                        _movies.value = ViewState.QuerySearch(result.value)
                    }
                }
                is DataResult.Fail -> {
                    _movies.value = ViewState.Fail(result.exception)
                }
            }
        }
    }

    fun getLatestMovies() {
        viewModelScope.launch {
            lastSearch = ""
            _movies.value = ViewState.Loading
            when (val result = GetLatestMoviesUseCase().execute(Unit)) {
                is DataResult.Success -> {
                    _movies.value = ViewState.InitialData(result.value)
                }
                is DataResult.Fail -> {
                    _movies.value = ViewState.Fail(result.exception)
                }
            }
        }
    }

    sealed class ViewState {
        data class InitialData(val value: List<Movie>) : ViewState()
        data class QuerySearch(val value: List<Movie>) : ViewState()
        data class Fail(val exception: Exception) : ViewState()
        object Loading : ViewState()
        object NoResults : ViewState()
    }
}
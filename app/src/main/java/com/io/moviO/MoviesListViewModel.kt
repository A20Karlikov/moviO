package com.io.moviO

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesListViewModel : ViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()

    init {
        _movies.value  = listOf(
            Movie("Movie 1", R.drawable.ic_launcher_background, "2000", "Drama", "Cast Crew", "Overview"),
            Movie("Movie 2", R.drawable.ic_launcher_background, "2001", "Comedy", "Cast Crew", "Overview"),
            Movie("Movie 3", R.drawable.ic_launcher_background, "2002", "Drama", "Cast Crew", "Overview"),
            Movie("Movie 4", R.drawable.ic_launcher_background, "2003", "Comedy", "Cast Crew", "Overview"),
            Movie("Movie 5", R.drawable.ic_launcher_background, "2004", "Drama", "Cast Crew", "Overview"),
            Movie("Movie 6", R.drawable.ic_launcher_background, "2005", "Comedy", "Cast Crew", "Overview"),
            Movie("Movie 7", R.drawable.ic_launcher_background, "2006", "Drama", "Cast Crew", "Overview"),
            Movie("Movie 8", R.drawable.ic_launcher_background, "2007", "Comedy", "Cast Crew", "Overview"),
            Movie("Movie 9", R.drawable.ic_launcher_background, "2008", "Drama", "Cast Crew", "Overview"),
            Movie("Movie 10", R.drawable.ic_launcher_background, "2009", "Comedy", "Cast Crew", "Overview"))
    }

    fun getMovies(): LiveData<List<Movie>> = _movies

}
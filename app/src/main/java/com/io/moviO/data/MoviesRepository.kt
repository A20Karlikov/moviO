package com.io.moviO.data

import com.io.moviO.R

object MoviesRepository {

    private var moviesData = mutableListOf<Movie>()

    fun getMovies(): List<Movie> {
        setMovies()
        return moviesData
    }

    private fun setMovies() {
        moviesData = listOf(
            Movie(
                "1",
                "Movie 1",
                R.drawable.ic_launcher_background,
                "2000",
                "Drama",
                "Cast Crew",
                "Overview"
            ),
            Movie(
                "2",
                "Movie 2",
                R.drawable.ic_launcher_background,
                "2001",
                "Comedy",
                "Cast Crew",
                "Overview"
            ),
            Movie(
                "3",
                "Movie 3",
                R.drawable.ic_launcher_background,
                "2002",
                "Drama",
                "Cast Crew",
                "Overview"
            ),
            Movie(
                "4",
                "Movie 4",
                R.drawable.ic_launcher_background,
                "2003",
                "Comedy",
                "Cast Crew",
                "Overview"
            ),
            Movie(
                "5",
                "Movie 5",
                R.drawable.ic_launcher_background,
                "2004",
                "Drama",
                "Cast Crew",
                "Overview"
            ),
            Movie(
                "6",
                "Movie 6",
                R.drawable.ic_launcher_background,
                "2005",
                "Comedy",
                "Cast Crew",
                "Overview"
            ),
            Movie(
                "7",
                "Movie 7",
                R.drawable.ic_launcher_background,
                "2006",
                "Drama",
                "Cast Crew",
                "Overview"
            ),
            Movie(
                "8",
                "Movie 8",
                R.drawable.ic_launcher_background,
                "2007",
                "Comedy",
                "Cast Crew",
                "Overview"
            ),
            Movie(
                "9",
                "Movie 9",
                R.drawable.ic_launcher_background,
                "2008",
                "Drama",
                "Cast Crew",
                "Overview"
            ),
            Movie(
                "10",
                "Movie 10",
                R.drawable.ic_launcher_background,
                "2009",
                "Comedy",
                "Cast Crew",
                "Overview"
            )
        ) as MutableList<Movie>
    }
}
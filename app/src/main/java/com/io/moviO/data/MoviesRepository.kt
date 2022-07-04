package com.io.moviO.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MoviesRepository {

    private val moviesData: List<Movie> = mutableListOf(
        Movie(
            "1",
            "The Shawshank Redemption",
            "https://m.media-amazon.com/images/I/71P4mPuMizL._AC_SY679_.jpg",
            "2000",
            "Drama",
            "Cast Crew",
            "Overview"
        ),
        Movie(
            "2",
            "The Godfather",
            "https://m.media-amazon.com/images/I/618dnyEsIuL._AC_SL1500_.jpg",
            "2001",
            "Comedy",
            "Cast Crew",
            "Overview"
        ),
        Movie(
            "3",
            "The Dark Knight",
            "https://static.wikia.nocookie.net/althistory/images/d/d1/The_Dark_Knight.jpg/revision/latest?cb=20201016152714",
            "2002",
            "Drama",
            "Cast Crew",
            "Overview"
        ),
        Movie(
            "4",
            "Schindler's List",
            "https://www.themoviedb.org/t/p/original/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg",
            "2003",
            "Comedy",
            "Cast Crew",
            "Overview"
        ),
        Movie(
            "5",
            "The Lord of the Rings: The Return of the King",
            "https://cdn.shopify.com/s/files/1/0057/3728/3618/products/c468809972e1fb4b7b0c56b07db68987_a25dc22c-c83a-479d-a241-10fa55e7b36b_500x.jpg?v=1573588780",
            "2004",
            "Drama",
            "Cast Crew",
            "Overview"
        ),
        Movie(
            "6",
            "Forrest Gump",
            "https://www.themoviedb.org/t/p/original/h5J4W4veyxMXDMjeNxZI46TsHOb.jpg",
            "2005",
            "Comedy",
            "Cast Crew",
            "Overview"
        ),
        Movie(
            "7",
            "The Matrix",
            "https://static.posters.cz/image/1300/posters/matrix-%D0%A5%D0%B0%D0%BA%D0%B5%D1%80%D0%B8-i104636.jpg",
            "2006",
            "Drama",
            "Cast Crew",
            "Overview"
        ),
        Movie(
            "8",
            "Star Wars",
            "https://m.media-amazon.com/images/I/A1wnJQFI82L._AC_SL1500_.jpg",
            "2007",
            "Comedy",
            "Cast Crew",
            "Overview"
        ),
        Movie(
            "9",
            "Gladiator",
            "https://m.media-amazon.com/images/I/71sj8Yt20qL._AC_SY679_.jpg",
            "2008",
            "Drama",
            "Cast Crew",
            "Overview"
        ),
        Movie(
            "10",
            "The Prestige",
            "https://img.fruugo.com/product/1/14/14328141_max.jpg",
            "2009",
            "Comedy",
            "Cast Crew",
            "Overview"
        )
    )

    suspend fun getMovies(): List<Movie> = withContext(Dispatchers.IO) { moviesData }
}
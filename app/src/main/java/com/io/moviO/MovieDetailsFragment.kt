package com.io.moviO

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.io.moviO.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

        val movie = Movie(
            "THE SHAWSHANK REDEMPTION",
            R.drawable.shawshank_poster,
            "1994",
            "Drama\nCrime",
            "Morgan Freeman\nTim Robbins",
            "The Shawshank Redemption is a 1994 American drama film written and directed by Frank Darabont, based on the 1982 Stephen King novella Rita Hayworth and Shawshank Redemption. It tells the story of banker Andy Dufresne (Tim Robbins), who is sentenced to life in Shawshank State Penitentiary for the murders of his wife and her lover, despite his claims of innocence. Over the following two decades, he befriends a fellow prisoner, contraband smuggler Ellis \"Red\" Redding (Morgan Freeman), and becomes instrumental in a money-laundering operation led by the prison warden Samuel Norton (Bob Gunton). William Sadler, Clancy Brown, Gil Bellows, and James Whitmore appear in supporting roles.\nDarabont purchased the film rights to King's story in 1987, but development did not begin until five years later, when he wrote the script over an eight-week period. Two weeks after submitting his script to Castle Rock Entertainment, Darabont secured a \$25 million budget to produce The Shawshank Redemption, which started pre-production in January 1993. While the film is set in Maine, principal photography took place from June to August 1993 almost entirely in Mansfield, Ohio, with the Ohio State Reformatory serving as the eponymous penitentiary. The project attracted many stars of the time for the role of Andy, including Tom Hanks, Tom Cruise, and Kevin Costner. Thomas Newman provided the film's score."
        )

        fillMovieDetails(movie)
    }

    private fun fillMovieDetails(movie: Movie){
        binding.movieName.text = movie.name
        binding.moviePoster.setBackgroundResource(movie.poster)
        binding.movieYear.text = movie.year
        binding.movieGenre.text = movie.gerne
        binding.movieCast.text = movie.cast
        binding.overviewText.text = movie.overview
    }

}
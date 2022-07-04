package com.io.moviO.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.io.moviO.R
import com.io.moviO.data.DataResult
import com.io.moviO.databinding.FragmentMovieDetailsBinding

private const val ARG_MOVIE_ID = "movie_id"

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var binding: FragmentMovieDetailsBinding
    val viewModel: MovieDetailsViewModel by lazy { ViewModelProvider(this).get(MovieDetailsViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        requireArguments().getString(ARG_MOVIE_ID)?.let { viewModel.getMovieById(it) }
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataResult.Success ->
                    binding.apply {
                        movieName.text = it.value.name
                        movieYear.text = it.value.year
                        movieGenre.text = it.value.gerne
                        movieCast.text = it.value.cast
                        overviewText.text = it.value.overview
                        Glide.with(this@MovieDetailsFragment).load(it.value.poster)
                            .into(moviePoster)
                    }
                is DataResult.Fail -> Toast.makeText(
                    this.context,
                    R.string.error_message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    companion object {
        fun newInstance(id: String): MovieDetailsFragment = MovieDetailsFragment().also {
            it.arguments = Bundle().apply { putString(ARG_MOVIE_ID, id) }
        }
    }
}
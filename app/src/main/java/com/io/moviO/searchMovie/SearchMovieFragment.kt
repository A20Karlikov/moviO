package com.io.moviO.searchMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.io.moviO.R
import com.io.moviO.databinding.FragmentSearchMovieBinding
import com.io.moviO.domain.DataResult
import com.io.moviO.moviesList.MovieListAdapter
import java.util.*
import kotlin.concurrent.schedule

class SearchMovieFragment : Fragment(R.layout.fragment_search_movie),
    MovieListAdapter.OnMovieClickedListener {
    private lateinit var binding: FragmentSearchMovieBinding
    private var timer = Timer()
    val viewModel: SearchMovieViewModel by lazy { ViewModelProvider(this).get(SearchMovieViewModel::class.java) }
    private var adapter = MovieListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchMovieBinding.bind(view)

        binding.searchMovieEt.doAfterTextChanged {
            if (it.toString().isEmpty()) {
                viewModel.getLatestMovies()
            }
            if (it.toString().length == 3) {
                viewModel.searchMovie(it.toString())
            }
            if (it.toString().length > 3) {
                binding.searchMovieRv.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.VISIBLE
                timer.cancel()
                Timer().schedule(3000) {
                    if (it.toString().isEmpty()) {
                        viewModel.getLatestMovies()
                    } else {
                        viewModel.searchMovie(it.toString())
                    }
                }
            }
        }


        viewModel.movies.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.searchMovieRv.visibility = View.VISIBLE
                    adapter.updateMovieList(it.value)
                }
                is DataResult.Fail -> Toast.makeText(
                    this.context,
                    R.string.error_message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.searchMovieRv.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getLatestMovies()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_search_movie, container, false)

    override fun onItemClicked(id: Int) = Navigation.findNavController(binding.root)
        .navigate(SearchMovieFragmentDirections.actionSearchToDetails(id))
}
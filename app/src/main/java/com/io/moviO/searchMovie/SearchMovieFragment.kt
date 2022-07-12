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
import com.io.moviO.moviesList.MovieListAdapter
import java.util.*
import kotlin.concurrent.schedule

private const val QUERY_SEARCH_MIN_CHARS: Int = 2

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
            timer.cancel()
            timer = Timer()
            showLoading(true)
            timer.schedule(2000) {
                if (it.toString().length < QUERY_SEARCH_MIN_CHARS) {
                    viewModel.getLatestMovies()
                } else {
                    viewModel.searchMovie(it.toString())
                }
            }
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            when (it) {
                is SearchMovieViewModel.ViewState.Loading -> showLoading(true)
                is SearchMovieViewModel.ViewState.InitialData -> {
                    binding.searchTextResult.text = getString(R.string.search_text_default_result)
                    showLoading(false)
                    adapter.updateMovieList(it.value)
                    binding.searchMovieRv.scrollToPosition(0)
                }
                is SearchMovieViewModel.ViewState.QuerySearch -> {
                    binding.searchTextResult.text = "Results for \"${binding.searchMovieEt.text}\""
                    showLoading(false)
                    adapter.updateMovieList(it.value)
                    binding.searchMovieRv.scrollToPosition(0)
                }
                is SearchMovieViewModel.ViewState.NoResults -> {
                    binding.searchTextResult.text =
                        "No results for \"${binding.searchMovieEt.text}\""
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.searchTextResult.visibility = View.VISIBLE
                }
                is SearchMovieViewModel.ViewState.Fail -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(
                        this.context,
                        R.string.error_message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        binding.searchMovieRv.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_search_movie, container, false)

    override fun onItemClicked(id: Int) = Navigation.findNavController(binding.root)
        .navigate(SearchMovieFragmentDirections.actionSearchToDetails(id))

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.apply {
                searchMovieRv.visibility = View.INVISIBLE
                searchTextResult.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                progressBar.visibility = View.INVISIBLE
                searchMovieRv.visibility = View.VISIBLE
                searchTextResult.visibility = View.VISIBLE
            }
        }
    }
}
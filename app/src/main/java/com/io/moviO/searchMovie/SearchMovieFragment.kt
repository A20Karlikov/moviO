package com.io.moviO.searchMovie

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
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
    private var searchText = ""
    val viewModel: SearchMovieViewModel by lazy { ViewModelProvider(this).get(SearchMovieViewModel::class.java) }
    private var adapter = MovieListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchMovieBinding.bind(view)
        setHasOptionsMenu(true)

        viewModel.movies.observe(viewLifecycleOwner) {
            val hasData =
                it is SearchMovieViewModel.ViewState.InitialData || it is SearchMovieViewModel.ViewState.QuerySearch
            binding.progressBar.isVisible = it is SearchMovieViewModel.ViewState.Loading
            binding.searchMovieRv.isVisible = hasData
            binding.searchTextResult.isVisible =
                hasData || it is SearchMovieViewModel.ViewState.NoResults

            when (it) {
                is SearchMovieViewModel.ViewState.Loading -> Unit
                is SearchMovieViewModel.ViewState.InitialData -> {
                    binding.searchTextResult.text = getString(R.string.search_text_default_result)
                    adapter.updateMovieList(it.value)
                    binding.searchMovieRv.scrollToPosition(0)
                }
                is SearchMovieViewModel.ViewState.QuerySearch -> {
                    binding.searchTextResult.text = "Results for \"${searchText}\""
                    adapter.updateMovieList(it.value)
                    binding.searchMovieRv.scrollToPosition(0)
                }
                is SearchMovieViewModel.ViewState.NoResults -> binding.searchTextResult.text =
                    "No results for \"${searchText}\""

                is SearchMovieViewModel.ViewState.Fail -> Toast.makeText(
                    this.context,
                    R.string.error_message,
                    Toast.LENGTH_LONG
                ).show()

            }
        }
        binding.searchMovieRv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchView: SearchView = menu.findItem(R.id.search_toolbar).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                searchText = query.lowercase(Locale.getDefault())
                timer.cancel()
                timer = Timer()
                timer.schedule(2000) {
                    if (searchText.length < QUERY_SEARCH_MIN_CHARS) {
                        viewModel.getLatestMovies()
                    } else {
                        viewModel.searchMovie(searchText)
                    }

                }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_search_movie, container, false)

    override fun onItemClicked(id: Int) = Navigation.findNavController(binding.root)
        .navigate(SearchMovieFragmentDirections.showDetails(id))
}
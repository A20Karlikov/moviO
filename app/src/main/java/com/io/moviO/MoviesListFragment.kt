package com.io.moviO

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.io.moviO.adapter.MovieListAdapter
import com.io.moviO.databinding.FragmentMoviesListBinding

class MoviesListFragment : Fragment(R.layout.fragment_movies_list),
    MovieListAdapter.OnMovieClickedListener {
    private lateinit var binding: FragmentMoviesListBinding
    val viewModel: MoviesListViewModel by lazy { ViewModelProvider(this).get(MoviesListViewModel::class.java) }
    private var apadter = MovieListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel.movies.observe(viewLifecycleOwner) {
            apadter.updateMovieList(it)
        }

        binding = FragmentMoviesListBinding.bind(view)
        binding.moviesListRv.adapter = apadter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =  inflater.inflate(R.layout.fragment_movies_list, container, false)


    override fun onItemClicked(movie: Movie) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(movie))
            .addToBackStack(this::class.java.name)
            .commit()
    }

}
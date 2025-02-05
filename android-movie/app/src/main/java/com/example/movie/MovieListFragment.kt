package com.example.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movie.databinding.FragmentMovieListBinding
import com.example.movie.model.ListItem

class MovieListFragment : Fragment(), MovieListAdapterListener {
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        val movieList = MovieList.movieList
        movieListAdapter = MovieListAdapter(this)
        movieListAdapter.submitList(movieList)

        binding.mainRecyclerview.adapter = movieListAdapter
    }

    override fun onMovieClick(movie: ListItem.MovieItem) {
        val intent =
            MovieDetailActivity.createIntent(
                requireContext(),
                movie.title,
                movie.releaseTime,
                movie.runtime,
                movie.poster,
                movie.series,
            )
        startActivity(intent)
    }

    override fun onButtonClick(movie: ListItem.MovieItem) {
        val bottomSheetFragment = BottomSheetFragment.newInstance(movie.title)
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onAdClick(ad: ListItem.AdItem) {
        val uri = Uri.parse(ad.adUrl)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(requireContext().packageManager) == null) {
            return Toast.makeText(
                requireContext(),
                "No browser available to open this link",
                Toast.LENGTH_SHORT,
            ).show()
        } else {
            startActivity(intent)
        }
    }
}

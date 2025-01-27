package com.example.movie

import com.example.movie.model.ListItem

interface MovieListAdapterListener {
    fun onMovieClick(movie: ListItem.MovieItem)

    fun onButtonClick(movie: ListItem.MovieItem)

    fun onAdClick(ad: ListItem.AdItem)
}

package com.example.movie

interface MovieListAdapterListener {
    fun onMovieClick(movie: ListItem.MovieItem)
    fun onButtonClick(movie: ListItem.MovieItem)
    fun onAdClick(ad: ListItem.AdItem)
}

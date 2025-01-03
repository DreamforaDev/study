package com.example.movie

interface MovieAdapterListener {
    fun onMovieClick(movie: ListItem.MovieItem)
    fun onButtonClick(movie: ListItem.MovieItem)
    fun onAdClick(ad: ListItem.AdItem)
}

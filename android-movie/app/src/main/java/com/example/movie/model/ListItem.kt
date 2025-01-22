package com.example.movie.model

sealed class ListItem {
    data class MovieItem(
        val series: Int,
        val poster: Int,
        val title: String,
        val releaseTime: String,
        val runtime: String
    ) : ListItem()

    data class AdItem(
        val adImg: Int,
        val adUrl: String
    ) : ListItem()
}

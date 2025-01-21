package com.example.movie

enum class MovieListItemViewType(val type: Int) {
    MOVIE_ITEM(0),
    AD_ITEM(1);

    companion object{
        fun fromInt(type : Int):MovieListItemViewType {
            return entries.find { it.type == type }
                ?: throw IllegalArgumentException("Invalid view type(${type})")
        }
    }
}

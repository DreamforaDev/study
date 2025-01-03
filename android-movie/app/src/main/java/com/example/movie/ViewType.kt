package com.example.movie

enum class ViewType(val type: Int) {
    MOVIE_ITEM(0),
    AD_ITEM(1);

    companion object{
        fun fromInt(type : Int):ViewType {
            return values().find { it.type == type }
                ?: throw IllegalArgumentException("Invalid view type(${type}")
        }
    }
}

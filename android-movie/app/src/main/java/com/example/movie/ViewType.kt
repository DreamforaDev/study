package com.example.movie

enum class ViewType(val type: Int) {
    MOVIE_ITEM(0),
    AD_ITEM(1);

    companion object {
        fun fromInt(value: Int): ViewType {
            return values().firstOrNull { it.type == value }
                ?: throw IllegalArgumentException("Invalid ViewType value")
        }
    }
}

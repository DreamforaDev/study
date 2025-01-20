package com.example.movie

enum class PreferenceKeys(val key: String) {
    SHARED_PREFERENCE_NAME("reservedMovies"),
    MOVIES_LIST("movies_list"),
    DATE("date"),
    TIME("time"),
    THEATER_NAME("theater_name"),
    MOVIE_TITLE("movie_title");
}

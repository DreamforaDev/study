package com.example.movie

import android.content.Context
import com.example.movie.model.ReservedMovie

class ReservationDetailsPresenter(
    private val view: ReservationDetailsContract.View,
    private val context: Context
) : ReservationDetailsContract.Presenter {
    override fun loadReservedMovies() {
        val sharedPreferences = context.getSharedPreferences(
            PreferenceKeys.SHARED_PREFERENCE_NAME.key,
            Context.MODE_PRIVATE
        )

        val jsonString = sharedPreferences.getString(PreferenceKeys.MOVIES_LIST.key, "[]")
        val jsonArray = org.json.JSONArray(jsonString)

        if (jsonArray.length() == 0) {
            view.showEmptyState()
            return
        }

        val reservedMoviesList = mutableListOf<ReservedMovie>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val reservedMovie = ReservedMovie(
                movieTitle = if (jsonObject.has(PreferenceKeys.MOVIE_TITLE.key)) jsonObject.getString(
                    PreferenceKeys.MOVIE_TITLE.key
                ) else "Unknown Title",
                theaterName = if (jsonObject.has(PreferenceKeys.THEATER_NAME.key)) jsonObject.getString(
                    PreferenceKeys.THEATER_NAME.key
                ) else "Unknown Theater",
                dateTime = if (jsonObject.has(PreferenceKeys.DATE_TIME.key)) jsonObject.getString(
                    PreferenceKeys.DATE_TIME.key
                ) else "Unknown DateTime",

                )
            reservedMoviesList.add(reservedMovie)
        }
        showReservedMovies(reservedMoviesList)
    }

    private fun showReservedMovies(reservedMoviesList: MutableList<ReservedMovie>) {
        view.displayReservedMovies(reservedMoviesList)
    }
}

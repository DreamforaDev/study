package com.example.movie.viewModel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.PreferenceKeys
import com.example.movie.model.ReservedMovie

class ReservationDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(
            PreferenceKeys.SHARED_PREFERENCE_NAME.key,
            Application.MODE_PRIVATE,
        )

    private val _reservedMovies = MutableLiveData<List<ReservedMovie>>()
    val reservedMovies: LiveData<List<ReservedMovie>> get() = _reservedMovies

    fun loadReservedMovies() {
        val savedMoviesJson = sharedPreferences.getString(PreferenceKeys.MOVIES_LIST.key, "[]")
        val savedMoviesArray = org.json.JSONArray(savedMoviesJson)

        val reservedMoviesList = mutableListOf<ReservedMovie>()

        for (i in 0 until savedMoviesArray.length()) {
            val jsonObject = savedMoviesArray.getJSONObject(i)
            val movie =
                ReservedMovie(
                    movieTitle =
                        if (jsonObject.has(PreferenceKeys.MOVIE_TITLE.key)) {
                            jsonObject.getString(
                                PreferenceKeys.MOVIE_TITLE.key,
                            )
                        } else {
                            "Unknown Title"
                        },
                    theaterName =
                        if (jsonObject.has(PreferenceKeys.THEATER_NAME.key)) {
                            jsonObject.getString(
                                PreferenceKeys.THEATER_NAME.key,
                            )
                        } else {
                            "Unknown Theater"
                        },
                    dateTime =
                        if (jsonObject.has(PreferenceKeys.DATE_TIME.key)) {
                            jsonObject.getString(
                                PreferenceKeys.DATE_TIME.key,
                            )
                        } else {
                            "Unknown DateTime"
                        },
                )
            reservedMoviesList.add(movie)
        }

        _reservedMovies.value = reservedMoviesList
        Log.d("_reservedMovies updated", "_reservedMovies : ${_reservedMovies.value}")
    }
}

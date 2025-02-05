package com.example.movie.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.movie.PreferenceKeys
import com.example.movie.model.Reservation
import com.example.movie.model.ReservedMovie
import org.json.JSONArray
import org.json.JSONObject
import java.time.format.DateTimeFormatter

class ReservationRepository(private val context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            PreferenceKeys.SHARED_PREFERENCE_NAME.key,
            Context.MODE_PRIVATE,
        )
    }

    fun saveReservationToPref(reservation: Reservation) {
        val existingDataJson = sharedPreferences.getString(PreferenceKeys.MOVIES_LIST.key, "[]")
        val existingDataList = JSONArray(existingDataJson)

        val formattedDateTime = reservation.dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))

        val newReservation =
            JSONObject().apply {
                put(
                    PreferenceKeys.DATE_TIME.key,
                    formattedDateTime,
                )
                put(
                    PreferenceKeys.THEATER_NAME.key,
                    reservation.theaterName,
                )
                put(
                    PreferenceKeys.MOVIE_TITLE.key,
                    reservation.movieTitle,
                )
            }

        existingDataList.put(newReservation)
        sharedPreferences.edit()
            .putString(PreferenceKeys.MOVIES_LIST.key, existingDataList.toString()).apply()
    }

    fun loadReservedMovies(): List<ReservedMovie> {
        val savedMoviesJson = sharedPreferences.getString(PreferenceKeys.MOVIES_LIST.key, "[]")
        val savedMoviesArray = JSONArray(savedMoviesJson)

        val reservedMoviesList = mutableListOf<ReservedMovie>()

        for (i in 0 until savedMoviesArray.length()) {
            val jsonObject = savedMoviesArray.getJSONObject(i)
            val movie =
                ReservedMovie(
                    movieTitle =
                        if (jsonObject.has(PreferenceKeys.MOVIE_TITLE.key)) {
                            jsonObject.getString(PreferenceKeys.MOVIE_TITLE.key)
                        } else {
                            "Unknown Title"
                        },
                    theaterName =
                        if (jsonObject.has(PreferenceKeys.THEATER_NAME.key)) {
                            jsonObject.getString(PreferenceKeys.THEATER_NAME.key)
                        } else {
                            "Unknown Theater"
                        },
                    dateTime =
                        if (jsonObject.has(PreferenceKeys.DATE_TIME.key)) {
                            jsonObject.getString(PreferenceKeys.DATE_TIME.key)
                        } else {
                            "Unknown DateTime"
                        },
                )
            reservedMoviesList.add(movie)
        }
        return reservedMoviesList
    }

    companion object {
        const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
    }
}

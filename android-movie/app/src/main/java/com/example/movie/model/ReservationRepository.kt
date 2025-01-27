package com.example.movie.model

import android.content.Context
import android.content.SharedPreferences
import com.example.movie.PreferenceKeys
import org.json.JSONArray
import org.json.JSONObject

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

        val newReservation =
            JSONObject().apply {
                put(
                    PreferenceKeys.DATE_TIME.key,
                    reservation.dateTime,
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
}

package com.example.movie

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.movie.model.Reservation
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationCompletedPresenter(
    private val view: ReservationCompletedContract.View,
    private val context: Context
) : ReservationCompletedContract.Presenter {

    private fun setTicketPrice(ticketNumber: Int): Int {
        return ticketNumber * 10_000
    }

    private fun buildReservation(
        movieTitle: String,
        theaterName: String,
        ticketNumber: Int,
        dateTime: LocalDateTime
    ): Reservation {
        val reservation = Reservation.build(
            movieTitle = movieTitle,
            theaterName = theaterName,
            ticketNumber = ticketNumber,
            ticketPrice = setTicketPrice(ticketNumber),
            dateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"))
        )
        return reservation
    }

    override fun showReservationDetails(
        movieTitle: String,
        theaterName: String,
        ticketNumber: Int,
        dateTime: LocalDateTime
    ) {
        view.displayReservationDetails(
            buildReservation(
                movieTitle,
                theaterName,
                ticketNumber,
                dateTime
            )
        )
    }

    override fun saveReservation(
        movieTitle: String,
        theaterName: String,
        ticketNumber: Int,
        dateTime: LocalDateTime
    ) {
        val sharedPreferences =
            context.getSharedPreferences(
                PreferenceKeys.SHARED_PREFERENCE_NAME.key,
                Context.MODE_PRIVATE
            )
        val edit = sharedPreferences.edit()

        val (existingDataList, newReservation) = createReservationJson(
            sharedPreferences,
            dateTime,
            theaterName,
            movieTitle
        )

        existingDataList.put(newReservation)
        edit.putString(PreferenceKeys.MOVIES_LIST.key, existingDataList.toString())
        edit.apply()

        Log.d("ReservationCompleted!", "Updated Data List : $existingDataList")
        view.showToast("예매 성공!")
    }

    private fun createReservationJson(
        sharedPreferences: SharedPreferences,
        dateTime: LocalDateTime,
        theaterName: String,
        movieTitle: String
    ): Pair<JSONArray, JSONObject> {
        val existingDataJson = sharedPreferences.getString(PreferenceKeys.MOVIES_LIST.key, "[]")
        val existingDataList = JSONArray(existingDataJson)

        val newReservation = JSONObject().apply {
            put(
                PreferenceKeys.DATE_TIME.key,
                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            )
            put(
                PreferenceKeys.THEATER_NAME.key,
                theaterName
            )
            put(
                PreferenceKeys.MOVIE_TITLE.key,
                movieTitle
            )
        }
        return Pair(existingDataList, newReservation)
    }
}

package com.example.movie

import android.content.Context
import android.util.Log
import com.example.movie.model.Reservation
import org.json.JSONArray
import org.json.JSONObject
import java.util.Locale

class ReservationCompletedPresenter(
    private val view: ReservationCompletedContract.View,
    private val context: Context
) : ReservationCompletedContract.Presenter {


    override fun saveReservation(movieTitle: String, theaterName: String, ticketNumber: Int) {
        val sharedPreferences =
            context.getSharedPreferences(
                PreferenceKeys.SHARED_PREFERENCE_NAME.key,
                Context.MODE_PRIVATE
            )
        val edit = sharedPreferences.edit()

        val existingDataJson = sharedPreferences.getString(PreferenceKeys.MOVIES_LIST.key, "[]")
        val existingDataList = JSONArray(existingDataJson)

        val newReservation = JSONObject().apply {
            put(PreferenceKeys.DATE.key, getCurrentFormattedDate())
            put(PreferenceKeys.TIME.key, getCurrentFormattedTime())
            put(
                PreferenceKeys.THEATER_NAME.key,
                theaterName
            )
            put(
                PreferenceKeys.MOVIE_TITLE.key,
                movieTitle
            )
        }

        existingDataList.put(newReservation)

        Log.d("ReservationCompleted!", "Updated Data List : $existingDataList")
        view.showToast("예매 성공!")

        edit.putString(PreferenceKeys.MOVIES_LIST.key, existingDataList.toString())
        edit.apply()
    }

    override fun getReservationDetails(movieTitle: String, theaterName: String, ticketNumber: Int) {
        val ticketPrice = ticketNumber * 10_000
        val formattedDate = getCurrentFormattedDate()
        val formattedTime = getCurrentFormattedTime()

        val reservation = Reservation(
            movieTitle = movieTitle,
            theaterName = theaterName,
            ticketNumber = ticketNumber,
            ticketPrice = ticketPrice,
            formattedDate = formattedDate,
            formattedTime = formattedTime
        )

        view.displayReservationDetails(reservation)
    }

    private fun getCurrentFormattedDate(): String {

        val calendar = java.util.Calendar.getInstance()
        val dateFormatter = java.text.SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return dateFormatter.format(calendar.time)
    }

    private fun getCurrentFormattedTime(): String {
        val calendar = java.util.Calendar.getInstance()
        val timeFormatter = java.text.SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        return timeFormatter.format(calendar.time)

    }

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd"
        private const val TIME_FORMAT = "HH:mm"
    }
}

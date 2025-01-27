package com.example.movie.viewModel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.PreferenceKeys
import com.example.movie.model.Reservation
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _reservation = MutableLiveData<Reservation>()
    val reservation: LiveData<Reservation> get() = _reservation

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(
            PreferenceKeys.SHARED_PREFERENCE_NAME.key,
            Application.MODE_PRIVATE,
        )

    private fun setTicketPrice(ticketNumber: Int): Int {
        return ticketNumber * 10_000
    }

    fun setReservationDetails(
        movieTitle: String,
        theaterName: String,
        ticketNumber: Int,
        dateTime: LocalDateTime,
    ) {
        val reservation =
            Reservation.build(
                movieTitle = movieTitle,
                theaterName = theaterName,
                ticketNumber = ticketNumber,
                ticketPrice = setTicketPrice(ticketNumber),
                dateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")),
            )
        _reservation.value = reservation
    }

//   sharedPref 에 저장
    fun saveReservationToPref() {
        _reservation.value?.let { reservation ->
            val existingDataJson = sharedPreferences.getString(PreferenceKeys.MOVIES_LIST.key, "[]")
            val existingDataList = JSONArray(existingDataJson)

            val newReservation =
                JSONObject().apply {
                    put(
                        PreferenceKeys.DATE_TIME.key,
                        reservation.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
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
            sharedPreferences.edit().putString(PreferenceKeys.MOVIES_LIST.key, existingDataList.toString()).apply()

            Log.d("ReservationCompleted! save to sharedPreference", "Updated Data List : $existingDataList")
            _toastMessage.value = "예매 성공!"
        }
    }
}

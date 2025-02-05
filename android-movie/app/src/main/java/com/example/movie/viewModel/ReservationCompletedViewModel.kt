package com.example.movie.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.model.Reservation
import com.example.movie.repository.ReservationRepository
import java.time.LocalDateTime

class ReservationCompletedViewModel(application: Application) : AndroidViewModel(application) {
    private val _reservation = MutableLiveData<Reservation>()
    val reservation: LiveData<Reservation> get() = _reservation

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val repository = ReservationRepository(application)

    private fun setTicketPrice(ticketNumber: Int): Int {
        return ticketNumber * TICKET_PRICE_PER_UNIT
    }

    fun saveReservationDetails(
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
                dateTime = dateTime,
            )
        _reservation.value = reservation

        // sharedPref에 저장
        repository.saveReservationToPref(reservation)

        _toastMessage.value = "예매 성공!"
    }

    companion object {
        private const val TICKET_PRICE_PER_UNIT = 10_000
    }
}

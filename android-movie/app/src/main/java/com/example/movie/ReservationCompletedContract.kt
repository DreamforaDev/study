package com.example.movie

import com.example.movie.model.Reservation
import java.time.LocalDateTime

interface ReservationCompletedContract {
    interface View {
        fun showToast(message: String)
        fun displayReservationDetails(reservation: Reservation)

    }

    interface Presenter {
        fun showReservationDetails(movieTitle: String, theaterName: String, ticketNumber: Int, dateTime: LocalDateTime)

        fun saveReservation(movieTitle: String, theaterName: String, ticketNumber: Int, dateTime: LocalDateTime)

    }
}

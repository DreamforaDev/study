package com.example.movie

import com.example.movie.model.Reservation

interface ReservationCompletedContract {
    interface View {
        fun showToast(message: String)
        fun displayReservationDetails(reservation: Reservation)

    }

    interface Presenter {
        fun saveReservation(movieTitle: String, theaterName: String, ticketNumber: Int)

        fun getReservationDetails(movieTitle: String, theaterName: String, ticketNumber: Int)
    }
}

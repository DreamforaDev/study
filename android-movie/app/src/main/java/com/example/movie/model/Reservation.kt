package com.example.movie.model

import java.time.LocalDateTime

data class Reservation(
    val movieTitle: String,
    val theaterName: String,
    val ticketNumber: Int,
    val ticketPrice: Int,
    val dateTime: LocalDateTime,
) {
    companion object {
        fun build(
            movieTitle: String,
            theaterName: String,
            ticketNumber: Int,
            ticketPrice: Int,
            dateTime: LocalDateTime,
        ) = Reservation(
            movieTitle,
            theaterName,
            ticketNumber,
            ticketPrice,
            dateTime,
        )
    }
}

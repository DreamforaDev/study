package com.example.movie.model

data class Reservation(
    val movieTitle: String,
    val theaterName: String,
    val ticketNumber: Int,
    val ticketPrice: Int,
    val dateTime: String,
) {
    companion object {
        fun build(
            movieTitle: String,
            theaterName: String,
            ticketNumber: Int,
            ticketPrice: Int,
            dateTime: String,
        ) = Reservation(
            movieTitle,
            theaterName,
            ticketNumber,
            ticketPrice,
            dateTime,
        )
    }
}

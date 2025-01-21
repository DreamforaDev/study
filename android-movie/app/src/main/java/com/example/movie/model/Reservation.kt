package com.example.movie.model

data class Reservation(
    val movieTitle: String,
    val theaterName: String,
    val ticketNumber: Int,
    val ticketPrice: Int,
    val formattedDate: String,
    val formattedTime: String
)

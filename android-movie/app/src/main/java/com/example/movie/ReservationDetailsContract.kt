package com.example.movie

import com.example.movie.model.ReservedMovie

interface ReservationDetailsContract {

    interface View{
        fun displayReservedMovies(movies:List<ReservedMovie>)
        fun showEmptyState()
    }
    interface Presenter{
        fun loadReservedMovies()
    }
}
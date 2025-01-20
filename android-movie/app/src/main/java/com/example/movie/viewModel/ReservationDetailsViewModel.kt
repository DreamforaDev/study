package com.example.movie.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie.model.ReservedMovie

class ReservationDetailsViewModel : ViewModel() {
    val reservations = MutableLiveData<MutableList<ReservedMovie>>(mutableListOf())

    fun addReservation(reservation: ReservedMovie) {
        val currentList = reservations.value ?: mutableListOf()
        currentList.add(reservation)
        reservations.value = currentList
    }
}

package com.example.movie.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.model.ReservedMovie
import com.example.movie.repository.ReservationRepository

class ReservationDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val _reservedMovies = MutableLiveData<List<ReservedMovie>>()
    val reservedMovies: LiveData<List<ReservedMovie>> get() = _reservedMovies

    private val repository = ReservationRepository(application)

    // ViewModel이 생성될 때 자동으로 데이터 로드
    init {
        loadReservedMovies()
    }

    fun loadReservedMovies() {
        _reservedMovies.value = repository.loadReservedMovies()
        Log.d("_reservedMovies updated", "_reservedMovies : ${_reservedMovies.value}")
    }
}

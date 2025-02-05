package com.example.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movie.databinding.FragmentReservationDetailsBinding
import com.example.movie.viewModel.ReservationDetailsViewModel

class ReservationDetailsFragment : Fragment() {
    private lateinit var binding: FragmentReservationDetailsBinding
    private lateinit var adapter: ReservationDetailsAdapter
    private val viewModel: ReservationDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentReservationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ReservationDetailsFragment", "예매 내역 프래그먼트 뷰 생성됨")

        adapter = ReservationDetailsAdapter()
        binding.reservationDetailsRecyclerview.adapter = adapter

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.loadReservedMovies()
    }
}

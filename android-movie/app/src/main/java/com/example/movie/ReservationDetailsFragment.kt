package com.example.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.FragmentReservationDetailsBinding
import com.example.movie.model.ReservedMovie

class ReservationDetailsFragment : Fragment(),ReservationDetailsContract.View {

    private lateinit var binding: FragmentReservationDetailsBinding
    private lateinit var adapter: ReservationDetailsAdapter
    private lateinit var presenter: ReservationDetailsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        presenter = ReservationDetailsPresenter(this,requireContext())
        presenter.loadReservedMovies()
    }

    private fun setupRecyclerView() {
        adapter = ReservationDetailsAdapter()
        binding.reservationDetailsRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ReservationDetailsFragment.adapter
        }
    }

    override fun showReservedMovies(movies: List<ReservedMovie>) {
        adapter.submitList(movies)
        binding.emptyStateTv.visibility=View.GONE
        binding.reservationDetailsRecyclerview.visibility=View.VISIBLE

    }

    override fun showEmptyState() {
        binding.emptyStateTv.visibility=View.VISIBLE
        binding.reservationDetailsRecyclerview.visibility=View.GONE
    }
}

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

class ReservationDetailsFragment : Fragment() {

    private lateinit var binding: FragmentReservationDetailsBinding
    private lateinit var adapter: ReservationDetailsAdapter

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
        loadReservedMoviesFromSharedPreferences()
    }

    private fun setupRecyclerView() {
        adapter = ReservationDetailsAdapter()
        binding.reservationDetailsRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ReservationDetailsFragment.adapter
        }
    }

    private fun loadReservedMoviesFromSharedPreferences() {
        val sharedPreferences = requireContext().getSharedPreferences(
            PreferenceKeys.SHARED_PREFERENCE_NAME.key,
            Context.MODE_PRIVATE
        )
        val jsonString = sharedPreferences.getString(PreferenceKeys.MOVIES_LIST.key, "[]")
        val jsonArray = org.json.JSONArray(jsonString)

        if (jsonArray.length() == 0) {
            adapter.submitList(emptyList())
            return
        }

        val reservedMoviesList = mutableListOf<ReservedMovie>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val reservedMovie = ReservedMovie(
                movieTitle = if (jsonObject.has(PreferenceKeys.MOVIE_TITLE.key)) jsonObject.getString(
                    PreferenceKeys.MOVIE_TITLE.key
                ) else "Unknown Title",
                theaterName = if (jsonObject.has(PreferenceKeys.THEATER_NAME.key)) jsonObject.getString(
                    PreferenceKeys.THEATER_NAME.key
                ) else "Unknown Theater",
                date = if (jsonObject.has(PreferenceKeys.DATE.key)) jsonObject.getString(
                    PreferenceKeys.DATE.key
                ) else "Unknown Date",
                time = if (jsonObject.has(PreferenceKeys.TIME.key)) jsonObject.getString(
                    PreferenceKeys.TIME.key
                ) else "Unknown Time"
            )
            reservedMoviesList.add(reservedMovie)
        }
        adapter.submitList(reservedMoviesList)
    }
}

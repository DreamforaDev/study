package com.example.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemReservedMovieBinding
import com.example.movie.model.ReservedMovie

class ReservationDetailsAdapter
    : ListAdapter<ReservedMovie, ReservationDetailsAdapter.MovieViewHolder>(diffUtil) {

    inner class MovieViewHolder(private val binding: ItemReservedMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: ReservedMovie) {
            binding.tvMovieTitle.text = movie.movieTitle
            binding.tvTheaterName.text = movie.theaterName
            binding.tvReservationDate.text = movie.date
            binding.tvReservationTime.text = movie.time

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemReservedMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<ReservedMovie>() {
            override fun areItemsTheSame(oldItem: ReservedMovie, newItem: ReservedMovie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ReservedMovie,
                newItem: ReservedMovie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

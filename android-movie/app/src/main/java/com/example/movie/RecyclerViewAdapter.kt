package com.example.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemAdBinding
import com.example.movie.databinding.ItemMovieBinding

class RecyclerViewAdapter(
    private val onMovieClickListener: (ListItem.MovieItem) -> Unit,
    private val onButtonClickListener: (ListItem.MovieItem) -> Unit,
    private val onAdClickListener: (ListItem.AdItem) -> Unit
) : ListAdapter<ListItem, RecyclerView.ViewHolder>(diffUtil) {

    inner class MovieItemHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListItem.MovieItem) {
            binding.ivPoster.setImageResource(item.poster)
            binding.tvTitle.text = item.title
            binding.tvReleaseTime.text = item.releaseTime
            binding.tvRuntime.text = item.runtime

            binding.root.setOnClickListener {
                onMovieClickListener(item)
            }

            binding.cvReservationBtn.setOnClickListener {
                onButtonClickListener(item)
            }

        }

    }

    inner class AdItemHolder(val binding: ItemAdBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListItem.AdItem) {
            binding.imgAd.setImageResource(item.adImg)

            binding.root.setOnClickListener {
                onAdClickListener
            }

        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ListItem.MovieItem -> ViewType.MOVIE_ITEM.type
            is ListItem.AdItem -> ViewType.AD_ITEM.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewTypeEnum = ViewType.fromInt(viewType)
        return when (viewTypeEnum) {
            ViewType.MOVIE_ITEM -> {
                val view =
                    ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MovieItemHolder(view)
            }

            ViewType.AD_ITEM -> {
                val view = ItemAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AdItemHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type: ${viewType}")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ListItem.MovieItem -> (holder as MovieItemHolder).bind(item)
            is ListItem.AdItem -> (holder as AdItemHolder).bind(item)
        }
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}



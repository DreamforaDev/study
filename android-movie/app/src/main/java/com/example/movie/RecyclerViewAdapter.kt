package com.example.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemMovieBinding

class RecyclerViewAdapter : ListAdapter<Movie, RecyclerViewAdapter.MoviesHolder>(diffUtil) {

    inner class MoviesHolder(var binding: ItemMovieBinding )
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Movie){
            binding.ivPoster.setImageResource(item.poster)
            binding.tvTitle.text=item.title
            binding.tvReleaseTime.text=item.releaseTime
            binding.tvRuntime.text=item.runtime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MoviesHolder {

        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MoviesHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(getItem(position))

    }


    companion object{

        val diffUtil = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
package com.example.movie.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.ReservationDetailsAdapter
import com.example.movie.model.ReservedMovie

@BindingAdapter("app:items")
fun setItems(
    recyclerView: RecyclerView,
    items: List<ReservedMovie>?,
)  {
    val adapter = recyclerView.adapter as? ReservationDetailsAdapter
    adapter?.submitList(items ?: emptyList())
}
